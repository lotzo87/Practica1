package mx.com.idmexico.vvazquez.calculadora;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText eText;
    private String n1="",n2 ="", oper="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnSuma).setOnClickListener(this);
        findViewById(R.id.btnResta).setOnClickListener(this);
        findViewById(R.id.btnMult).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnMod).setOnClickListener(this);
        findViewById(R.id.btnRes).setOnClickListener(this);
        findViewById(R.id.btnDel).setOnClickListener(this);
        findViewById(R.id.btnPto).setOnClickListener(this);

        eText = (EditText) findViewById(R.id.txtRes);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                int d = Integer.parseInt(((Button) findViewById(v.getId())).getText().toString());
                eText.setText(eText.getText().toString().concat(String.format(Integer.toString(d))));
                if (oper.equals(""))
                    n1= String.valueOf(d);
                else
                    n2= String.valueOf(d);
            break;
            case R.id.btnDel:
                if (eText.getText().length() >0) {
                    eText.setText("");
                    n1="";
                    n2="";
                    oper="";
                }
                break;
            case R.id.btnPto:
                if (eText.getText().toString().indexOf(".") == -1)
                    if (eText.getText().toString()== "")
                        eText.setText(String.format("0").concat("."));
                    else
                        eText.setText(String.format(eText.getText().toString().concat(".")));

                break;
            case R.id.btnSuma:
                oper= "+";
                break;
            case R.id.btnResta:
                oper= "-";
                break;
            case R.id.btnMult:
                oper="*";
                break;
            case R.id.btnDiv:
                oper ="/";
                break;
            case R.id.btnMod:
                oper="%";
                break;
            case R.id.btnRes:
                if (!(n1.trim().equals(""))) {
                    eText.setText(calculate(oper, n1, n2.equals("") ? n1 : n2));
                    n1="";
                    n2="";
                    oper="";
                }


        }
    }

    public String calculate(String oper,String n1, String n2)
    {
        double d1 = Double.parseDouble(n1);
        double d2 = Double.parseDouble(n2);
        double r=0;
        switch (oper)
        {
            case "+":
                r= d1+d2;
                break;
            case "-":
                r= d1-d2;
                break;
            case "*":
                r= d1*d2;
                break;
            case "/":
                if (d2!=0)
                    r= d1/d2;
                else
                    return "Error";
                break;
            case "%":
                r= d1%d2;
                break;
        }
        return Double.toString(r);
    }
}
