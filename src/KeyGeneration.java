import java.util.Random;

/**
 * Created by yuta on 2017/05/25.
 */
public class KeyGeneration{
    int privateKey;
    int publicKey[] = new int[2];
    Random rnd = new Random();
    public void generation(int n,int[] point1)
    {
        Math math=new Math();
        //privateKey = rnd.nextInt(n);
        privateKey=131;
       // System.out.println("privateKey="+ privateKey);
        publicKey=math.fairy_mul(point1,privateKey);
        //System.out.println(privateKey);
        //System.out.println("(" + publicKey[0] + ", " + publicKey[1] + ")");
        //return keySet;
    }
    int[] getPublicKey(){
        return publicKey;
    }
    int getPrivateKey(){
        return privateKey;
    }

}
