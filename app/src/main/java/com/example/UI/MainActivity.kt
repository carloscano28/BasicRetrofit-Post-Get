package com.example.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaptador.PublicacionAdapter
import com.example.config.InicioAplicacion.Companion.webServiceGlobal
import com.example.demoretrofitdos.R
import com.example.demoretrofitdos.databinding.ActivityMainBinding
import com.example.models.Publicacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recuoerarPublicaciones()

        insertarPublicacion()


    }

    fun recuoerarPublicaciones (){

        var callRespuesta= webServiceGlobal.recuperaPublicaciones()  // avisamos que objeto o api va a recuperar
        callRespuesta.enqueue(object :Callback<ArrayList<Publicacion>>{
            override fun onResponse(
                call: Call<ArrayList<Publicacion>>,
                response: Response<ArrayList<Publicacion>>
            ) {
                if(response.isSuccessful){    // valida el tipo de error
                    val listaConsumida = response.body()
                    if(listaConsumida!=null && listaConsumida.size>0){  // Que no sea vacia
                       /* listaConsumida.forEach{
                            var id= it.id           // it hace referencia a cada elemento de la lista
                            var title= it.title
                            Log.d("Mensaje", "El mensaje es $id . El title es $title")
                        }*/

                        var adaptador = PublicacionAdapter(listaConsumida)
                        binding.recyclerUno.adapter= adaptador
                        binding.recyclerUno.layoutManager= LinearLayoutManager(applicationContext)


                    }
                    else{
                        Toast.makeText(applicationContext, "Lista vacia", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Publicacion>>, t: Throwable) {

                Toast.makeText(applicationContext, "falta peticion", Toast.LENGTH_SHORT).show()
            }


        })           //enqueve es sincorno y execute es asincorno
    }


    fun insertarPublicacion(){
        var mPublicacion = Publicacion(50, 0, "ESte es el titulo nuevo","Este es el cuerpo de la nueva public" )

        var callRespuesta = webServiceGlobal.instertarPublicacion(mPublicacion)

        callRespuesta.enqueue(object :Callback<Publicacion>{
            override fun onResponse(call: Call<Publicacion>, response: Response<Publicacion>) {

                if(response.isSuccessful){
                     // !! asegura que no es null porque sabemos que en este caso no es null
                    var nueva:Publicacion  = response.body()!!


                        var mensaje= "publicacion creaca con el id ${nueva.id}"

                        mensaje+=  "title ${nueva.title}"
                        mensaje+= " user  ${nueva.userId}"
                        mensaje+= " body  ${nueva.body}"

                        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_SHORT).show()



                }else{

                    Toast.makeText(applicationContext, "No se inserto   ${response.code()}", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<Publicacion>, t: Throwable) {

            }

        })

    }



}