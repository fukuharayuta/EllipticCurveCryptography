import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yuta on 2017/05/25.
 */
public class Math {
    Parameters p = new Parameters();
    List<Integer[]> list = new ArrayList<>();

    int mod = p.mod;
    int a1 = p.a1;
    int a2 = p.a2;
    int a3 = p.a3;
    int a4 = p.a4;
    int a6 = p.a6;
    int result;
    int n_prime;
    //int n;
    //int result;



    public String hash(String algorithmName, String value) {
        MessageDigest md = null;
        StringBuilder sb = null;
        try {
            md = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md.update(value.getBytes());
        sb = new StringBuilder();
        for (byte b : md.digest()) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        return sb.toString();
    }

    public int orderMap() {
        //public int[] orderMap(int a,int b) {
        // List<List<Integer>> pointList = new ArrayList<>();
        // List<String> list = new ArrayList<>();

        Integer[] point;
        Integer[] pointView;
        Integer[] pointNew;
        int n = 0;
        int index = 278;
        int[] point_a = new int[2];

        int[] point_b = new int[2];

        for (int x = 0; x < mod; x++) {

            for (int y = 0; y <= mod / 2; y++) {

                int light = (y * y + a1 * x * y + a3 * y) % mod;
                int left = (x * x * x + a2 * x * x + a4 * x + a6) % mod;

                if (light == left) {
                    //System.out.println("(" + x + ", " + y + ")");
                    //String point="(" + x + ", " + y + ")";
                    point = new Integer[2];
                    point[0] = x;
                    point[1] = y;
                    list.add(point);
                    // pointList.add(list);
                    if (y != 0) {
                        // System.out.println("(" + x + ", " + (mod-y) + ")");
                        //point= "(" + x + ", " + (mod-y) + ")";
                        point = new Integer[2];
                        point[0] = x;
                        point[1] = (mod - y);
                        // System.out.println("(x,y)=" + "(" + point[0] + ", " + point[1] + ")");
                        list.add(point);
                        //pointList.add(list);
                    }
                    break;
                }
            }
        }

//        for( int i = 0; i < list.size(); i++ ) {
//            pointView=list.get(i);
//            System.out.println("(x,y,i)=" + "(" + pointView[0] + "," + pointView[1] + "," + i + ")");
//            if(i != 1){
//                if(pointView[0] ==257 && pointView[1] ==2136){
//                    System.out.println("exiting");
//
//                }
//            }
//                //System.out.println("(x,y,i)=" + "(" + pointView[0] + "," + pointView[1] + "," + i + ")");
//        }


        pointNew = list.get(index);
        point_a[0] = pointNew[0];
        point_a[1] = pointNew[1];

        //point_a=fairy_mul(point_a,21);
        System.out.println("(x,y)=" + "(" + point_a[0] + ", " + point_a[1] + ")");

       //int falg_if=0;
//        for (int i = 1; i < list.size() + 10; i++) {
//            //pointView=list.get(i);
//            point_b = fairy_mul(point_a, i);
//            System.out.println("(x1,y1,i)=" + "(" + point_b[0] + ", " + point_b[1] + ", " + i + ")");
//            if (i != 1) {
//                if (point_b[0] == 0 && point_b[1] == 0) {
//                    result = i;
//                    break;
//                    //falg_if=1;
//                    //System.out.println("rrrrrrr");
//                } else {
//                }
//            } else {
//            }
//
//        }

//        for (int i = 1; i < list.size(); i++) {
//            //pointView=list.get(i);
//            point_b = fairy_mul(point_a, i);
//            System.out.println("(x1,y1,i)=" + "(" + point_b[0] + ", " + point_b[1] + ", " + i + ")");
//            if (i != 1) {
//                if (point_b[0] == 257 && point_b[1] == 2136) {
//                    //result = i;
//                    System.out.println("true exiting");
//                    break;
//                    //falg_if=1;
//                    //System.out.println("rrrrrrr");
//                } else {
//                }
//            } else {
//            }
//
//        }

        //System.out.println(list.size());
        n = list.size() + 1;
        //System.out.println("n=" + n);
//        System.out.println("n=" + n);
        //n_prime=maxPrimeJudge(n);
        //System.out.println("n_prime=" + n_prime);
        return n;
    }
    public int order(List list,int[] point_a) {
        int result_n = 0;
        int[] point_b;

        System.out.println();
        System.out.println("(order.x,order.y)=(" + point_a[0] + "," + point_a[1] + ")");

        for (int i = 1; i < list.size() + 10; i++) {
            //pointView=list.get(i);
            point_b = fairy_mul(point_a, i);
            System.out.println("(x1,y1,i)=" + "(" + point_b[0] + ", " + point_b[1] + ", " + i + ")");
            if (i != 1) {
                if (point_b[0] == 0 && point_b[1] == 0) {
                    result_n = i;
                    break;
                    //falg_if=1;
                    //System.out.println("rrrrrrr");
                } else {
                }
            } else {
            }

        }

        return result_n;
    }

    public int getResult(){return result;}
    public int getNPrime() {
        return n_prime;
    }
    //public int getN() { return  n;}
    public List getList() {return  list;}
    //public int[] getPoint() { return point_a; }



    public int[] fairy_mul(int[] point, int times) {
        int result[] = new int[2];
        if (times == 0) {
            point[0] = 0;
            point[1] = 0;
            return point;
        } else if (times == 1) {
            return point;
        } else {
            result = point;
            for (int i = 2; i <= times; i++) {
                result = fairy_add(result, point);
            }
        }

        return result;
    }

    public int[] fairy_add(int[] point1, int[] point2) {
        int lambda = 0;
        int niu = 0;
        int molecule1;
        int denominator1;
        int molecule2;
        int denominator2;
        int point[] = new int[2];
        int newPoint[] = new int[2];

//        System.out.println("(x1,y1)=" + "(" + point1[0] + ", " + point1[1] + ")");
//        System.out.println("(x2,y2)=" + "(" + point2[0] + ", " + point2[1] + ")");
//        System.out.println("mod=" + mod);
        //System.out.println("(a4,a6)=" + "(" + a4 + "," + a6 + ")");

        if(point1[0] == point2[0] && point1[1] != point2[1]){
            if((point1[1]+point2[1]) % mod==0){
                newPoint[0]=0;
                newPoint[1]=0;
                //System.out.println("mugen");
                return newPoint;
            }
        }
        else if (point1[0] == point2[0] && point1[1] == point1[1]) {
            molecule1 = (3 * point1[0] * point1[0] + 2 * a2 * point1[0] + a4 - a1 * point1[1]) % mod;
            denominator1 = (2 * point1[1] + a1 * point1[1] + a3) % mod;

            point[0] = molecule1;
            point[1] = denominator1;
            point = roop(point);

            lambda = (point[0] / point[1]) % mod;

            molecule2 = (-point1[0] * point1[0] * point1[0] + a4 * point1[0] + 2 * a6 - a3 * point1[1]) % mod;
            denominator2 = (2 * point1[1] + a1 * point1[0] + a3) % mod;

            point[0] = molecule2;
            point[1] = denominator2;
            point = roop(point);

            niu = (point[0] / point[1]) % mod;

        } else if (point1[0] != point2[0]) {
            molecule1 = point2[1] - point1[1];
            denominator1 = point2[0] - point1[0];

            point[0] = molecule1;
            point[1] = denominator1;
            point = roop(point);
            lambda = (point[0] / point[1]) % mod;

            molecule2 = point1[1] * point2[0] - point2[1] * point1[0];
            denominator2 = point2[0] - point1[0];

            point[0] = molecule2;
            point[1] = denominator2;
            point = roop(point);
            niu = (point[0] / point[1]) % mod;
        } else {

        }
        newPoint[0] = (lambda * lambda + a1 * lambda - a2 - point1[0] - point2[0]) % mod;
        newPoint[1] = (-(lambda + a1) * newPoint[0] - niu - a3) % mod;

        point1 = m_mod(newPoint, mod);


        return point1;
    }

    public int[] roop(int[] point) {
        int flag = 0;

        while (flag == 0) {
            if (point[0] % point[1] != 0) {
                point[0] = point[0] + mod;
            } else if (point[0] % point[1] == 0) {
                flag = 1;
            } else {
            }
        }
        return point;
    }

    public int[] m_mod(int[] point, int mod) {
        if (point[0] < 0) {
            point[0] = point[0] + mod;
        } else {

        }

        if (point[1] < 0) {
            point[1] = point[1] + mod;
        } else {

        }
        return point;
    }

    public int conversion(String s) {
        int result = 0;
        try {
            result = Integer.parseInt(s, 16);
        } catch (Exception e) {
            System.out.println("error");
            result = 0;
        }
        return result;
    }

    public int[] Divisor(int molecule, int denominator) {
        int[] result = new int[2];
        int maxCommonDivisor = getMaxCommonDivisor(molecule, denominator);
        molecule /= maxCommonDivisor;
        denominator /= maxCommonDivisor;
        result[0] = molecule;
        result[1] = denominator;

        return result;
    }

    public int getMaxCommonDivisor(int a, int b) {
        int mod = a % b;

        if (mod == 0) {
            return b;
        } else {
            return getMaxCommonDivisor(b, mod);
        }
    }

    public int PrimeJudge(int unknown) {
        int prime = 0;
        int count = 0;

        for (int i = 1; i < unknown; i++) {
            if (unknown % i == 0) {
                result = unknown / i;
                //System.out.println(result);
                count = 0;
                for (int j = 1; j <= result; j++) {
                    if (result % j == 0) {
                        count++;
                        //System.out.println("j=" + j);
                    }
                }
                //System.out.println("count=" + count);
                if (count == 2) {
                    prime=1;
                } else {
                    prime=0;
                }


            }
        }
        return prime;
    }
    public int maxPrimeJudge(int unknown) {
        int judge = 0;
        int result;
        int max = 0;
        int count;
        for (int i = 1; i < unknown; i++) {
            if (unknown % i == 0) {
                result = unknown / i;
                //System.out.println(result);
                count = 0;
                for (int j = 1; j <= result; j++) {
                    if (result % j == 0) {
                        count++;
                        //System.out.println("j=" + j);
                    }
                }
                //System.out.println("count=" + count);
                if (count == 2) {
                    if (max < result) {
                        max = result;
                    } else {
                    }
                } else {
                }
            } else {
            }
        }
        //System.out.println(max);
        return max;
    }

//    public void parametrChange(List list){
//        List<Integer[]> newList;
//        Integer[] seekPrimePoint;
//        int index=12;
//        int n_new;
//
//        newList=list;
//        seekPrimePoint=newList.get(index);
//        p.a4=seekPrimePoint[0];
//        p.a6=seekPrimePoint[1];
//        //n_new=seekPrimePoint[2];
//
//
//
//       // return n_new;
//
//    }

//    public List seekPrime() {
//        List<Integer[]> list = new ArrayList<>();
//        int result = 0;
//        int n_new;
//        int n_prime;
//        Integer[] pointNew;
//
//        for (int a = 1; a < 100; a++) {
//            for (int b = 1; b < 100; b++) {
//                a4 = a;
//                a6 = b;
//                //orderMap(a4, a6);
//                n_new=getN();
//                n_prime = maxPrimeJudge(n);
//
//                if (n_prime == n) {
//                    pointNew = new Integer[3];
//                    pointNew[0]=a4;
//                    pointNew[1]=a6;
//                    pointNew[2]=n_prime;
//                    list.add(pointNew);
//                   // System.out.println("n_prime=" + n_prime);
//                }
//
//            }
//        }
//
////        for (int i = 0; i < list.size(); i++) {
////            pointNew = list.get(i);
////            System.out.println("(a,b,i)" + "(" + pointNew[0] + "," + pointNew[1]  + "," + i +")");
////            //System.out.println(list.get(i));
////        }
//
//        return list;
//        }
}

