package vivian.com.calculator;

import android.renderscript.Byte2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button yi,er,san,si,wu,liu,qi,ba,jiu,ling,qingchu,shanchu,jia,jian,cheng,chu,dengyu,dian
            ,youkuohoa,zuokuohao;

    TextView result;
    StringBuilder  biaodashi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yi = (Button)findViewById(R.id.yi);
        yi.setOnClickListener(this);
        er = (Button)findViewById(R.id.er);
        er.setOnClickListener(this);
        san = (Button)findViewById(R.id.san);
        san.setOnClickListener(this);
        si = (Button)findViewById(R.id.si);
        si.setOnClickListener(this);
        wu = (Button)findViewById(R.id.wu);
        wu.setOnClickListener(this);
        liu = (Button)findViewById(R.id.liu);
        liu.setOnClickListener(this);
        qi = (Button)findViewById(R.id.qi);
        qi.setOnClickListener(this);
        ba = (Button)findViewById(R.id.ba);
        ba.setOnClickListener(this);
        jiu = (Button)findViewById(R.id.jiu);
        jiu.setOnClickListener(this);
        ling = (Button)findViewById(R.id.ling);
        ling.setOnClickListener(this);
        qingchu = (Button)findViewById(R.id.qingchu);
        qingchu.setOnClickListener(this);
        shanchu = (Button)findViewById(R.id.shanchu);
        shanchu.setOnClickListener(this);
        jia = (Button)findViewById(R.id.jia);
        jia.setOnClickListener(this);
        jian = (Button)findViewById(R.id.jian);
        jian.setOnClickListener(this);
        cheng = (Button)findViewById(R.id.cheng);
        cheng.setOnClickListener(this);
        chu = (Button)findViewById(R.id.chu);
        chu.setOnClickListener(this);
        dengyu = (Button)findViewById(R.id.dengyu);
        dengyu.setOnClickListener(this);
        dian = (Button)findViewById(R.id.dian);
        dian.setOnClickListener(this);
        zuokuohao = (Button)findViewById(R.id.zuokuohao);
        zuokuohao.setOnClickListener(this);
        youkuohoa = (Button)findViewById(R.id.youkuohao);
        youkuohoa.setOnClickListener(this);
        result = (TextView)findViewById(R.id.biaodashi);
        biaodashi = new StringBuilder();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.yi:
                biaodashi.append("1");
                result.setText(biaodashi);
                break;
            case R.id.er:
                biaodashi.append("2");
                result.setText(biaodashi);
                break;
            case R.id.san:
                biaodashi.append("3");
                result.setText(biaodashi);
                break;
            case R.id.si:
                biaodashi.append("4");
                result.setText(biaodashi);
                break;
            case R.id.wu:
                biaodashi.append("5");
                result.setText(biaodashi);
                break;
            case R.id.liu:
                biaodashi.append("6");
                result.setText(biaodashi);
                break;
            case R.id.qi:
                biaodashi.append("7");
                result.setText(biaodashi);
                break;
            case R.id.ba:
                biaodashi.append("8");
                result.setText(biaodashi);
                break;
            case R.id.jiu:
                biaodashi.append("9");
                result.setText(biaodashi);
                break;
            case R.id.ling:
                biaodashi.append("0");
                result.setText(biaodashi);
                break;
            case R.id.dian:
                biaodashi.append(".");
                result.setText(biaodashi);
                break;
            case R.id.jia:
                biaodashi.append("+");
                result.setText(biaodashi);
                break;
            case R.id.jian:
                biaodashi.append("-");
                result.setText(biaodashi);
                break;
            case R.id.cheng:
                biaodashi.append("x");
                result.setText(biaodashi);
                break;
            case R.id.chu:
                biaodashi.append("÷");
                result.setText(biaodashi);
                break;
            case R.id.youkuohao:
                biaodashi.append(")");
                result.setText(biaodashi);
                break;
            case R.id.zuokuohao:
                biaodashi.append("(");
                result.setText(biaodashi);
                break;
            case R.id.dengyu:

                if(kuohaopibei()&&biaodashi.length()>0){
                    String ss = calculate();
                    biaodashi = biaodashi.deleteCharAt(0);
                    biaodashi = biaodashi.deleteCharAt(biaodashi.length()-1);
                    result.setText(biaodashi+"\n"+"="+ss);
                    biaodashi = new StringBuilder();
                }else {
                    if(biaodashi.length()>0){
                        result.setText(biaodashi+"\n" +"表达式有错误");

                    }else {
                        result.setText("");
                    }
                    biaodashi.delete(0,biaodashi.length());
                }

                break;
            case R.id.qingchu:
                result.setText("");
                biaodashi = new StringBuilder();
                break;
            case R.id.shanchu:
                if(biaodashi.length()>0){
                    biaodashi.deleteCharAt(biaodashi.length()-1);
                    result.setText(biaodashi);
                }

                break;
        }

    }

    private  String calculate(){
        Stack<String> stringStack = new Stack<>();
        Stack<Double> doubleStack = new Stack<>();
        String string = "";

        biaodashi.insert(0,"(");
        biaodashi.append(")");
        Log.d("biaodashi",biaodashi.toString());
        string = biaodashi.toString();
        int provity = 0;
        int p = 0;
        int flag = 0;
        try {
        for(int i = 0;i<string.length();i++){
            char temp = string.charAt(i);
            char tt='c';char ttt = 'c';
            if(i != 1&&i!=0){
                tt= string.charAt(i-1);
            }
            if(i<string.length()-2){
               ttt = string.charAt(i+1);
            }

            switch (temp){
                case '+':

                case 'x':

                case '÷':
                    Log.d("+++",tt+"    "+ttt);
                        if(tt>=48&&tt<=57&&(ttt>=48&&ttt<=57||ttt == '('||ttt=='-')) {

                            p = provity(temp);
                            if (p > provity) {
                                stringStack.push(String.valueOf(temp));

                            } else {

                                calu(stringStack, doubleStack, temp);

                                stringStack.push(String.valueOf(temp));

                            }

                            if (stringStack.size() == 1) {
                                provity = 0;
                            } else {
                                provity = p;
                            }

                            Log.d("provity", String.valueOf(p));
                            Log.d("temp", String.valueOf(temp));
                        }else {
                            return "输入的表达式有错误";
                        }

                    break;
                case '-':

                    if((i==1 && ttt>=48&&ttt<=57) || ((tt == '+' || tt == '-' || tt == 'x' || tt == '÷')&& ttt>=48&&ttt<=57)){
                        int h = i+1;
                        for(;h<string.length()&&string.charAt(h)>=48&&string.charAt(h)<=57;h++);
                        int hh = h-1;
                        int hhh = 1;
                        double ddd = 0d;
                        for(;hh>i;hh--){
                            ddd = ddd + string.charAt(hh)-48*hhh;
                            hhh*=10;
                        }

                        doubleStack.push(-ddd);
                        i = h-1;
                    }else {
                      if(tt>=48&&tt<=57){
                          p = provity(temp);
                          if(p>provity){
                              stringStack.push(String.valueOf(temp));

                          }else {

                              calu(stringStack,doubleStack,temp);

                              stringStack.push(String.valueOf(temp));

                          }

                          if(stringStack.size() ==1){
                              provity = 0;
                          }else {
                              provity = p;
                          }

                      }else {
                          return "输入的表达式有错误";
                      }

                    }
                    break;
                case '(':
                    stringStack.push(String.valueOf(temp));
                    provity = 0;

                    break;
                case ')':

                       calu(stringStack,doubleStack,temp);

                    if(!stringStack.empty()){
                        provity=provity(stringStack.peek().charAt(0));
                    }
                    break;
                case '.':
                    char previcen = string.charAt(i-1);
                    char prepre = 'c';
                    if(i>=2){
                        prepre = string.charAt(i-2);
                    }
                    if(previcen < 48 || previcen >57 || prepre == '.'){
                        if((i!=1&&(previcen == '.' || previcen == ')'||previcen == '('))||(prepre == '.')){
                            return "输入的表达式有错误";
                        }
                        doubleStack.push(0d);

                    }
                    int j =i+1;
                    double d = 0.0;
                    int per = 10;
                    for(;j<string.length()&&string.charAt(j)>=48&&string.charAt(j)<=57;j++);
                    for(int k = i+1;k<j;k++){
                        int t = string.charAt(k)-48;
                        d = d+(double) t/per;
                        per*=10;
                    }
                    double dd = doubleStack.pop();
                    dd = dd+d;
                    doubleStack.push(dd);
                    i = j-1;
                    Log.d(".....",String.valueOf(d));

                    break;
                default:
                    int q = i;
                    for(;q<string.length()&&string.charAt(q) !='+'&&string.charAt(q) !='-'&&string.charAt(q) !='x'&&
                            string.charAt(q) !='÷'&&string.charAt(q) !=')'&&string.charAt(q) !='('
                            &&string.charAt(q) !='.';q++);
                    int pert = 1;
                    int qq = q-1;
                    double dou = 0;
                    for(;qq>=i;qq--){
                        dou = dou+string.charAt(qq)-48*pert;
                        pert*=10;
                    }
                    doubleStack.push(dou);
                    i = q-1;
                    Log.d("default",String.valueOf(dou));
                    Log.d("default_doublesize",String.valueOf(doubleStack.size()));
                    Log.d("default_i",String.valueOf(i));
                    break;


            }

        }
            if(doubleStack.size() !=1){
                return "输入的表达式有问题";
            }
            double res = doubleStack.pop();
            BigDecimal bg = new BigDecimal(res);
            double resul = bg.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();

            return String.valueOf(resul);
        }catch (ArithmeticException e){
            return " ∞";
        }
    }




    private int provity(char ch){
        switch (ch){
            case '+':
           case '-':
                return 1;
            case 'x':
            case '÷':
                return 2;
        }
        return 0;
    }

   private void calu(Stack<String> strings,Stack<Double> doubles,char temp)throws ArithmeticException{
       int pro = provity(temp);
       String s = strings.pop();
        Log.d("calu",s);
       while (pro<=provity(s.charAt(0)) &&!s.equals("(")){
           double num2 = doubles.pop();
           Log.d("double",String.valueOf(num2));
           double num1 = doubles.pop();
           double num3=0d;
           switch (s){
               case "+":
                   num3 = num1+num2;
                   break;
               case "-":
                   num3 = num1-num2;
                   break;
               case "x":
                   num3 = num1 * num2;
                   break;
               case "÷":
                   if(num2 == 0){
                       throw  new ArithmeticException("除数不能为零");
                   }
                   num3 = num1/num2;
                   break;
           }
           doubles.push(num3);
           if(strings.size()!=1){
               s = strings.pop();
           }else {
               s=strings.peek();
           }

           Log.d("calu",String.valueOf(strings.size()));

       }

   }

    private  boolean kuohaopibei(){
        String shizi = biaodashi.toString();
        StringBuilder kuohao = new StringBuilder();
        Stack<Character> zuokuohao = new Stack<>();
        for(int i =0;i<shizi.length();i++){
            if('(' == shizi.charAt(i) || ')' == shizi.charAt(i)){
                kuohao.append(shizi.charAt(i));
                if('(' == shizi.charAt(i)){
                    zuokuohao.push(shizi.charAt(i));
                }
            }
        }
        String ss = kuohao.toString();
        int j = 0;
        for(;j<ss.length()&&!zuokuohao.empty();j++){
            if(ss.charAt(j) == ')'){
                zuokuohao.pop();
            }
        }
        Log.d("pipei",String.valueOf(j)+"   "+String.valueOf(ss.length()));

        if(j<ss.length() || !zuokuohao.empty()){
            return  false;
        }
        return true;

    }

}
