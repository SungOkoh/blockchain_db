
public class Block {
    private int blockID;

    private String previousBlockHash;
    private String data;

    public Block(int blockID, String data, String previousBlockHash) {
        this.blockID = blockID;
        this.data = data;
        this.previousBlockHash=previousBlockHash;
    }


    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public void getInformation() {
        //안써도되는메소드
        System.out.println("--------------------------------------");

        System.out.println("블록 번호: " + getBlockID());
        System.out.println("이전 해시: " + getPreviousBlockHash());
        System.out.println("블록 데이터: " + getData());

        System.out.println("블록 해시: " + getBlockHash());

        System.out.println("--------------------------------------");
    }
    public String getBlockHash(){
        return Util.getHash(data+previousBlockHash);
    }
}