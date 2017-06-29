import java.math.BigInteger;

/**
 * Created by yuta on 2017/05/26.
 */
public class SignatureVerifiCation {

    Parameters p = new Parameters();
    int mod =p.mod;
    int a1=p.a1;
    int a2=p.a2;
    int a3=p.a3;
    int a4=p.a4;
    int a6=p.a6;

    public void signatureVerifiCation(int r, int s,String algorithmName,String message,int n,int[] point1,int[] publicKey){
        Math math =new Math();
        String e;
        int w;
        int flag=0;
        int s_rev=1;
        int u1;
        int u2;
        int v;
        int[] index =new int[2];
        int[] index1 =new int[2];
        int[] index2 =new int[2];
        int[] result;

        //System.out.println("check1");

       // System.out.println("r:" + r);
       // System.out.println("s:" + s);
        if (r >= 1 && r <= n-1){
            if (s >= 1 && s <= n-1){
                e=math.hash(algorithmName,message);
                System.out.println("e=" + e);
                //System.out.println("e=" + e);
                while (flag == 0){
                    if(s_rev % s != 0){
                        //System.out.println("check2");
                        //System.out.println(s_rev);
                        s_rev = s_rev + n;
                       result=math.Divisor(s_rev,s);
                       s_rev=result[0];
                       s=result[1];

                    }else if(s_rev % s == 0){
                        flag=1;
                    }
                    else{
                    }
                }
                //System.out.println("s:" + s);
                System.out.println("s_rev:" + s_rev);
                w=(s_rev/s) % n;
                System.out.println("w=" + w);
                /*eの掛け算はどうやって？*/
                //u1=(e*w) % n;

                BigInteger bi= new BigInteger(e,16);
                //System.out.println("bi:" + bi);
                u1=bi.mod(BigInteger.valueOf((long)n))
                        .multiply(BigInteger.valueOf((long)w))
                        .mod(BigInteger.valueOf((long)n))
                        .intValue();
                u2 =(r*w) % n;

                //System.out.println("u1=" + u1);
                //System.out.println("u2=" + u2);
                System.out.println("(u1,u2)=" + "(" + u1 + ", " + u2 + ")");

                index1=math.fairy_mul(point1,u1);
//                index1[0] =index1[0] % n;
//                index1[1] =index1[1] % n;
                //System.out.println("index1=" + "(" + index1[0] + "," + index1[1] + ")");
                //System.out.println("publicKey=" + "(" + publicKey[0] + "," + publicKey[1] + ")");
                index2=math.fairy_mul(publicKey,u2);
//                index1[0] =index2[0] % n;
//                index1[1] =index2[1] % n;
                //System.out.println("index2=" + "(" + index2[0] + "," + index2[1] + ")");
                index=math.fairy_add(index1,index2);
                System.out.println("(svc.x1,svc.y1)=" + "(" + index[0] + "," + index[1] + ")");
                v=index[0] % n;
                System.out.println("v=" + v);

                if(v==r){
                    System.out.println("true exit");
                }
                else {
                    System.out.println("false exit");
                }

            }
            else{

            }
        }
        else {
        }
    }
}
