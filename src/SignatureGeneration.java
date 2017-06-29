import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by yuta on 2017/05/26.
 */
public class SignatureGeneration {

    int flag1=0;
    int flag2=0;
    int flag3=0;
    int k;
    int k_rev=1;
    String e;
    int s;
    int r;



    Parameters p = new Parameters();
    int mod =p.mod;
    int a1=p.a1;
    int a2=p.a2;
    int a3=p.a3;
    int a4=p.a4;
    int a6=p.a6;

    public void SignatureGeneration(int n,int[] point1,String message,String algorithmName,int privateKey){
        Random rnd = new Random();
        Math math = new Math();
        //BigInteger bi = new BigInteger();
        int[] newPoint = new int[2];
        int[] result;

        //System.out.println("(a4,a6)=" +"(" + a4 +"," + a6 + ")");


        do{
            do {
                //k=rnd.nextInt(n);
                k=13;
                System.out.println("k=" + k);
                newPoint=math.fairy_mul(point1,k);
                System.out.println("(sg.x1,sg.y1)=" + "(" + newPoint[0] + ", " + newPoint[1] + ")");
                r=newPoint[0] % n;
                System.out.println("r=" + r);
                if (r==0){
                    flag1=1;
                }
            }while (flag1 == 1);

            //System.out.println("check(do~while_1)");

            while (flag2 == 0){
                if(k_rev % k != 0){
                    //System.out.println("check(k_rev)");
                    k_rev = k_rev + n;
                    //System.out.println("k_rev1==" + k_rev);
                    result=math.Divisor(k_rev,k);
                    k_rev=result[0];
                    k=result[1];

                }else if(k_rev % k == 0){
                    flag2=1;
                }
                else{
                }
            }

            //System.out.println("check2");

            k_rev =(k_rev/k) % n;
            System.out.println("(k_rev mod n)=" + k_rev);
            e=math.hash(algorithmName,message);
            BigInteger bi= new BigInteger(e,16);


            /*eの＋ってどういうこと？*/

           // System.out.println(Integer.parseInt(e));

            s=bi.add(BigInteger.valueOf((long)privateKey*r))
                    .mod(BigInteger.valueOf((long)n))
                    .multiply(BigInteger.valueOf((long)k_rev))
                    .mod(BigInteger.valueOf((long)n))
                    .intValue();

            //s=(k_rev*((bi.intValue() % mod)+privateKey*r))% n;
            //System.out.println("biginteger_Int=" + s);
           // System.out.println("bigInteger_mod" + bi.mod(bi,BigInteger.valueOf((long)n));
            //System.out.println("e_Intconver=" + bi.intValue());
            //System.out.println("e_Intconver_mod=" + bi.intValue() % mod);
            if(s==0){
                flag3=1;
            }
        }while (flag3==1);


        //System.out.println("k=" + k);
        System.out.println("e=" + e);
        System.out.println("s=" + s);

    }
    public int getR(){
        return r;
    }
    public int getS(){
        return s;
    }

}
