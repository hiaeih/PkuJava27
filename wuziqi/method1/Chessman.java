public enum Chessman {
	 BLACK("��"),WHITE("��");
	    private String chessman;
	     
	//  ˽�й��캯��
	    private Chessman(String chessman)
	    {
	        this.chessman=chessman;
	    }
	//  ���غ�����߰���
	    public String getChessman()
	    {
	        return this.chessman;
	         
	    }
}
