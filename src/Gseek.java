import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuta on 2017/05/28.
 */
public class Gseek {


    public void seek(List list){
        List<Integer[]> p;
        p=list;
        Math math =new Math();
        Integer[] point= new Integer[2];
        KeyGeneration kg=new KeyGeneration();
        SignatureGeneration sg=new SignatureGeneration();
        SignatureVerifiCation svc= new SignatureVerifiCation();
        Parameters parameter= new Parameters();
        int e;
        int g1;
        int g2;
        int r;
        int s;
        int privateKey;
        int publicKey[];
        String message=parameter.message;
        String algorithmName=parameter.algorithmName;




        for(int g = 0; g<p.size(); g++) {
            point=p.get(g);

           // g1 = (p.get(g);
           // g2 = (p.get(g);
            System.out.println("G=(" + point[0] + "," + point[1] + ")");

            int pointg[] = new int[2];
            pointg[0] = point[0];
            pointg[1] = point[1];
            int pointgt[] = new int[2];
            pointgt[0] = point[0];
            pointgt[1] = point[1];


            //nの探索
//            e = 1;
//            while (pointgt[0] != point[0] | pointgt[1] == point[1]) {
//                pointgt = math.fairy_mul(pointgt,);
//                e++;
//            }
//            e++;

            e=1;
            while (pointgt[0] != point[0] | pointgt[1] == point[1]){
                pointgt=math.fairy_add(pointg,pointgt);
                e++;
            }
            e++;
            //e=math.order(list,pointg);
            //e=math.getN();
            System.out.println("n(ord(G))=" + e);


            int n=math.PrimeJudge(e);
            System.out.println(n);
            //nの素数判定
            System.out.println("約数中の最大素数=" + n);

            //nが素数の場合
            if (n == 1) {
                int apoint[] = new int[2];

                kg.generation(n,pointg);
                privateKey=kg.getPrivateKey();
                publicKey=kg.getPublicKey();
                System.out.println("privateKey:"+ privateKey);
                System.out.println("publicKey:" + "(" + publicKey[0] + ", " + publicKey[1] + ")");


                //apoint = Key_Pair_Generation.publickey(d, g1, g2, mod, a, b);
                //System.out.println("Q(public key)=(" + apoint[0] + "," + apoint[1] + ")");

                int pointk[] = new int[2];
                pointk[0] = point[0];
                pointk[1] = point[1];

                sg.SignatureGeneration(n,pointg,message,algorithmName,privateKey);
                r=sg.getR();
                s=sg.getS();
                System.out.println("(r,s)=" + "(" + r + ", " + s + ")");


                svc.signatureVerifiCation(r,s,algorithmName,message,n,pointg,publicKey);

                //System.out.println("判定は" + Signature_Verification.verification(sign, m, e, a, b, mod, gpoint, apoint));
            }
        }
    }


}