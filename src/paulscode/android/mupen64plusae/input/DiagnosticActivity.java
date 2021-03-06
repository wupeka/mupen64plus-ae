/**
 * Mupen64PlusAE, an N64 emulator for the Android platform
 * 
 * Copyright (C) 2013 Paul Lamb
 * 
 * This file is part of Mupen64PlusAE.
 * 
 * Mupen64PlusAE is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * Mupen64PlusAE is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Mupen64PlusAE. If
 * not, see <http://www.gnu.org/licenses/>.
 * 
 * Authors: littleguy77
 */
package paulscode.android.mupen64plusae.input;

import java.util.Locale;

import paulscode.android.mupen64plusae.R;
import paulscode.android.mupen64plusae.input.provider.AbstractProvider;
import paulscode.android.mupen64plusae.persistent.AppData;
import paulscode.android.mupen64plusae.util.DeviceUtil;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.InputDevice.MotionRange;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

public class DiagnosticActivity extends Activity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.diagnostic_activity );
    }
    
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event )
    {
        return onKey( event );
    }
    
    @Override
    public boolean onKeyUp( int keyCode, KeyEvent event )
    {
        return onKey( event );
    }
    
    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        return onMotion( event );
    }
    
    @TargetApi( 12 )
    @Override
    public boolean onGenericMotionEvent( MotionEvent event )
    {
        return onMotion( event );
    }
    
    @TargetApi( Build.VERSION_CODES.HONEYCOMB_MR1 )
    private boolean onKey( KeyEvent event )
    {
        int key = event.getKeyCode();
        
        String message = "KeyEvent:";
        message += "\nDevice: " + getDeviceName( event.getDeviceId() );
        message += "\nAction: " + DeviceUtil.getActionName( event.getAction(), false );
        message += "\nKeyCode: " + key;
        
        if( AppData.IS_HONEYCOMB_MR1 )
        {
            message += "\n\n" + KeyEvent.keyCodeToString( key );
        }
        
        TextView view = (TextView) findViewById( R.id.textKey );
        view.setText( message );
        
        if( key == KeyEvent.KEYCODE_BACK )
        {
            if( event.getAction() == 0 )
                return super.onKeyDown( key, event );
            else
                return super.onKeyUp( key, event );
        }
        else
            return true;
    }
    
    @TargetApi( 12 )
    private boolean onMotion( MotionEvent event )
    {
        String message = "MotionEvent:";
        message += "\nDevice: " + getDeviceName( event.getDeviceId() );
        message += "\nAction: " + DeviceUtil.getActionName( event.getAction(), true );
        message += "\n";
        
        if( AppData.IS_GINGERBREAD )
        {
            for( MotionRange range : DeviceUtil.getPeripheralMotionRanges( event.getDevice() ) )
            {
                if( AppData.IS_HONEYCOMB_MR1 )
                {
                    int axis = range.getAxis();
                    String name = MotionEvent.axisToString( axis );
                    String source = DeviceUtil.getSourceName( range.getSource() ).toLowerCase(
                            Locale.ENGLISH );
                    float value = event.getAxisValue( axis );
                    message += String.format( "\n%s (%s): %+.2f", name, source, value );
                }
                else
                {
                    // TODO Something for Gingerbread devices
                }
            }
        }
        
        TextView view = (TextView) findViewById( R.id.textMotion );
        view.setText( message );
        return true;
    }
    
    private static String getDeviceName( int device )
    {
        String name = AbstractProvider.getHardwareName( device );
        return Integer.toString( device ) + ( name == null ? "" : " (" + name + ")" );
    }
}
