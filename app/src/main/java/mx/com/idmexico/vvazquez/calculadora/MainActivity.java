package mx.com.idmexico.vvazquez.calculadora;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.IntegerRes;
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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText eText;
    private String n1="",n2 ="", oper="";
    private RadioButton rbb, rbd;

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
        rbb = (RadioButton) findViewById(R.id.rbtnBin);
        rbb.setOnClickListener(this);
        rbd = (RadioButton)findViewById(R.id.rbtnDec);
        rbd.setOnClickListener(this);

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
                String d = eText.getText().toString() + ((Button)findViewById(v.getId())).getText().toString();
                if (oper.equals("")) {
                    n1 = d;
                    eText.setText(n1);
                }
                else {
                    n2 += d;
                   eText.setText(n2);
                }

            break;
            case R.id.btnDel:
                if (eText.getText().length() >0) {
                    eText.setText("");
                    clean();
                }
                break;
            case R.id.btnPto:
                String p = eText.getText().toString();
                if (p.length() == 0)
                {
                    p = String.format("0").concat(".");
                    if (oper.equals("")) {
                        n1 += p;
                        eText.setText(n1);
                    }
                    else {
                        n2 += p;
                        eText.setText(n2);
                    }
                }
                else
                {
                    if (p.indexOf(".") == -1) {
                        if (oper.equals("")) {
                            n1 += p;
                            eText.setText(n1);
                        }
                        else {
                            n2 += p;
                            eText.setText(n2);
                        }
                    }
                    else
                    {
                        if (oper.equals("")) {
                            n1 += p;
                            eText.setText(n1);
                        }
                        else {
                            n2 += p;
                            eText.setText(n2);
                        }
                    }
                }
                break;
            case R.id.btnSuma:
                oper= "+";
                eText.setText("");
                break;
            case R.id.btnResta:
                oper= "-";
                eText.setText("");
                break;
            case R.id.btnMult:
                oper="*";
                eText.setText("");
                break;
            case R.id.btnDiv:
                oper ="/";
                eText.setText("");
                break;
            case R.id.btnMod:
                oper="%";
                eText.setText("");
                break;
            case R.id.btnRes:
                if (!(n1.trim().equals(""))) {
                    double res = calculate(oper, n1, n2.equals("") ? n1 : n2);
                    eText.setText(rbd.isChecked()? String.format(String.valueOf(res)) : Integer.toBinaryString((int) res));
                    clean();
                    n1 = rbd.isChecked()? String.format(String.valueOf(res)) : Integer.toBinaryString((int) res);
                }
                break;
            case R.id.rbtnBin:
                enableButtons(false);
                eText.setText("");
                clean();
                break;
            case R.id.rbtnDec:
                enableButtons(true);
                eText.setText("");
                clean();
                break;


        }
    }

    private void clean()
    {
        n1="";
        n2="";
        oper="";
    }

    private void enableButtons(boolean b)
    {
        findViewById(R.id.btn2).setEnabled(b);
        findViewById(R.id.btn3).setEnabled(b);
        findViewById(R.id.btn4).setEnabled(b);
        findViewById(R.id.btn5).setEnabled(b);
        findViewById(R.id.btn6).setEnabled(b);
        findViewById(R.id.btn7).setEnabled(b);
        findViewById(R.id.btn8).setEnabled(b);
        findViewById(R.id.btn9).setEnabled(b);
        findViewById(R.id.btnMod).setEnabled(b);
        findViewById(R.id.btnPto).setEnabled(b);
    }


    public Double calculate(String oper,String n1, String n2)
    {
        double d1 = rbd.isChecked()? Double.parseDouble(n1): Integer.parseInt(n1,2);
        double d2 = rbd.isChecked()? Double.parseDouble(n2): Integer.parseInt(n2,2);
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
                    r=0;
                break;
            case "%":
                r= d1%d2;
                break;
        }
        //return Double.toString(r);
        return r;
    }


}
