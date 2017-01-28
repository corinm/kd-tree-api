public class Test {

    private byte[] b1;
    private byte[] b2;

    Test() {
        
    }

    public void storeb1(byte[] bytes) {
        this.b1 = bytes;
    }

    public void storeb2(byte[] bytes) {
        this.b2 = bytes;
    }

    public void compareBytes() {

        try {
            Object a = Serialiser.deserialise(b1);
            Object b = Serialiser.deserialise(b2);
            
            Boolean result1 = a.equals(b);
            System.out.println(result1);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean result2 = b1.equals(b2);
        System.out.println(result2);
    }

}
