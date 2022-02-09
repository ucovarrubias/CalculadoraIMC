package utiak.covarrubias.calculadoraimc
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de variables
        var lblResultado: TextView = findViewById(R.id.tvResultado)
        var lblEstado: TextView = findViewById(R.id.tvEstado)

        //Declaración editText
        var txtEstatura: EditText = findViewById(R.id.etEstatura)
        var txtPeso: EditText = findViewById(R.id.etPeso)
        var btnCalcular: Button = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener{
            if(!txtEstatura.text.isBlank() || !txtPeso.text.isBlank()){

                //Calcular IMC y muestra el resultado
                val imcNum = calcularIMC(txtEstatura.text.toString().toDouble(),
                    txtPeso.text.toString().toDouble())
                lblResultado.setText(imcNum.toString())

                //Se obtiene y muestra el estado del usuario
                val estado = this.obtenEstado(imcNum)
                lblEstado.setText(estado)

                //Se añade un color al estado dependiendo del resutaldo
                when(estado){
                    "Bajo peso" -> lblEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> lblEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> lblEstado.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad de grado 1" -> lblEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 2" -> lblEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 3" -> lblEstado.setBackgroundResource(R.color.colorRed)
                }
            }
        }
    }

    /**
     * Función que calcula el IMC en base al peso y altura
     */
    fun calcularIMC(altura: Double, peso: Double): Double{
        val imc: Double = (peso/(Math.pow(altura, 2.0)))
        return imc
    }

    fun obtenEstado(imc: Double): String {
        when {
            imc < 18.5 -> return "Bajo peso"
            imc >= 18.5 && imc <= 24.9 -> return "Saludable"
            imc >= 25 && imc <= 29.9 -> return "Sobrepeso"
            imc >= 30 && imc <= 34.9 -> return "Obesidad de grado 1"
            imc >= 35 && imc <= 39.9 -> return "Obesidad de grado 2"
            imc >= 40 -> return "Obesidad de grado 3"
        }
        return "Error"
    }
}