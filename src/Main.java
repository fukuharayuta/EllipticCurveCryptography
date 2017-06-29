import java.util.*;

public class Main {

    public static void main(String[] args) {
        Math math = new Math();
        Gseek gs = new Gseek();
        KeyGeneration kg = new KeyGeneration();
        Parameters p= new Parameters();
        SignatureGeneration sg= new SignatureGeneration();
        SignatureVerifiCation svc = new SignatureVerifiCation();
        int n=0;
        int point1[]=new int[2];
        int point2[]=new int[2];
        int newPoint[];
        int times=3;
        int privateKey;
        int publicKey[]=new int[2];
        int r;
        int s;
        int[] result;
        int n_prime=0;
        int index=12;
        int[] parameter= new int[3];

        String e;
        String message=p.message;
        String algorithmName=p.algorithmName;
        List<Integer[]> list = new ArrayList<>();
        Integer[] seekPrime;


        //point1[0]=5;
        //point1[1]=1;

        //point2[0]=5;
        //point2[1]=16;


        //point2=math.fairy_add(point1,point2);
        //point2=math.fairy_mul(point1,19);
        //System.out.println("G=" + "(" + point2[0] + ", " + point2[1] + ")");
        //list = math.seekPrime();
        //seekPrime=list.get(index);
        //parameter[0]=seekPrime[0];
        //parameter[1]=seekPrime[1];
        //n=seekPrime[2];
        //System.out.println("(a,b,n(main))" + "(" + p.a4 + ", " + p.a6 + "," + n + ")");

        //位数の計算

//        math.parametrChange(list);
//        point1=math.getPoint();
//        result=math.orderMap(parameter[0],parameter[1]);

        //math.orderMap();
        //gs.seek(math.getList());
        //gs.seek();

        System.out.println("(a4,a6)=" + "(" + p.a4 + "," + p.a6 + ")");
        System.out.println("algorithmName=" + algorithmName);
        System.out.println("message=" + message);
        point1[0]=12;
        point1[1]=2295;
        System.out.println("(G)=" + "(" + point1[0] + ", " + point1[1] + ")");
        //System.out.println("(a,b)=" + "(" + parameter[0] + ", " + parameter[1] + ")");


        //point1[0]=parameter[0];
        //point1[1]=parameter[1];

        //n=1789;

        //n=math.getN();
        //n=math.getResult();

        n=443;
        //n_prime=math.maxPrimeJudge(n);
        System.out.println("n=" + n);
        //System.out.println("n_prime=" + n_prime);
        //n=n_prime;
        //System.out.println("n_result=" + math.getResult());
        //n=math.maxPrimeJudge(3528);

        //System.out.println("n_prime(main)="+ n);



        //鍵の生成
        kg.generation(n,point1);
        privateKey=kg.getPrivateKey();
        publicKey=kg.getPublicKey();
        System.out.println("privateKey:"+ privateKey);
        System.out.println("publicKey:" + "(" + publicKey[0] + ", " + publicKey[1] + ")");

        //System.out.println("check1");

        //署名生成
        sg.SignatureGeneration(n,point1,message,algorithmName,privateKey);
        r=sg.getR();
        s=sg.getS();
        System.out.println("(r,s)=" + "(" + r + ", " + s + ")");

        //署名検証
        svc.signatureVerifiCation(r,s,algorithmName,message,n,point1,publicKey);


        // newPoint=math.fairy_add(point1,point2,a1,a2,a3,a4,a6,mod);
        //newPoint=math.fairy_mul(point1,times,a1,a2,a3,a4,a6,mod);
        //System.out.println("(" + newPoint[0] + ", " + newPoint[1] + ")");

       // e=math.hash(algorithmName,message);
       // System.out.println(e);

    }
}
