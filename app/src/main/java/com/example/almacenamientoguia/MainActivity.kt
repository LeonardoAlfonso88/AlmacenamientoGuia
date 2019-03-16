package com.example.almacenamientoguia

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    //Atributos
    lateinit private var contadorAlmacenado : String

    //Métodos
    //Constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //almacenamientoInterno()
        //memoriaInterna()
    }

    //Getter y Setters
    fun getContadorAlmacenado() = contadorAlmacenado

    fun setContadorAlmacenado(contadorAlmacenado : String)
    {
        this.contadorAlmacenado = contadorAlmacenado
    }

    //Almacenamiento Interno
    fun almacenamientoInterno()
    {
        val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
        this.setContadorAlmacenado(preferencias.getString("entradas",""))
        val textoContador = findViewById<TextView>(R.id.contador)
        val textoInicial = findViewById<TextView>(R.id.inicial)
        val editor = preferencias.edit()
        var contador: Int = 0

        if("".equals(this.getContadorAlmacenado()))
        {
            contador = 0
            textoInicial.text = "Primera vez en el app"
        }
        else
        {
            contador = Integer.parseInt(this.getContadorAlmacenado())
            textoInicial.text = "Ya has entrado"
        }

        contador += 1
        editor.putString("entradas", contador.toString())
        editor.commit()
        textoContador.text = contador.toString()
    }

    fun guardarTexto()
    {
        try {
            val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
            val mensaje = findViewById<EditText>(R.id.texto).text.toString()
            archivo.write(mensaje)
            archivo.flush()
            val mensajeFlotante = Toast.makeText(this, "Mensa", Toast.LENGTH_SHORT)
            mensajeFlotante.show()
        }
        catch(e: IOException){
        }
    }

    fun mostrarTexto(view: View)
    {
        val textoMemoria = findViewById<TextView>(R.id.memoria)

        if (fileList().contains("notas.txt"))
        {
            val archivo = InputStreamReader(openFileInput("notas.txt"))
            val br = BufferedReader(archivo)
            var linea = br.readLine()
            val mensaje = StringBuilder()
            while (linea != null) {
                mensaje.append(linea + "\n")
                linea = br.readLine()
            }
            br.close()
            archivo.close()
            textoMemoria.text = mensaje
        }
        else
        {
            val mensajeFlotante = Toast.makeText(this, "Que no existe el archivo Mano!!", Toast.LENGTH_SHORT)
            mensajeFlotante.show()
        }
    }




}
