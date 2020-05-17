package com.example.ladm_u4_practica1_raygozalopezmartin

import android.app.PendingIntent.getActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log


class IncomingCall : BroadcastReceiver() {

    var cursor : Context ?= null
    var contesto = true
    var i = 0
    val nombreBaseDatos = "contesta"

    override fun onReceive(context : Context, intent: Intent?) {
        try {
            cursor = context
            val tmgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            val PhoneListener = MyPhoneStateListener()

            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE)

        } catch (e: Exception) {
            Log.e("Phone Receive Error", " $e")
        }
    }

    private inner class MyPhoneStateListener : PhoneStateListener() {

        override fun onCallStateChanged(state: Int, incomingNumber: String) {
            Log.d("MyPhoneListener", "$state   incoming no:$incomingNumber")

            if(state == 2){
                contesto = false
            }

            if (state == 0 && contesto == true) {
                val num = "$incomingNumber"
                Log.d("LLamadaPerdida", num)
                i++
                    try {
                        if(!num.isEmpty()) {
                            var baseDatos = BaseDatos(cursor!!, nombreBaseDatos, null, 1)
                            var insertar = baseDatos.writableDatabase
                            var SQL = "INSERT INTO LLAMADASPERDIDAS VALUES (NULL ,'${num}', 'false')"

                            insertar.execSQL(SQL)
                            baseDatos.close()
                            Log.d("Insercion", "SE A INSERTADO CORRECTAMENTE --- " + i)
                        }
                    } catch (err : Exception) {

                    }
            }
        }
    }
}