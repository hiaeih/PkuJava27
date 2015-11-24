public enum Chessman {
	 BLACK("●"),WHITE("○");
	    private String chessman;
	     
	//  私有构造函数
	    private Chessman(String chessman)
	    {
	        this.chessman=chessman;
	    }
	//  返回黑棋或者白棋
	    public String getChessman()
	    {
	        return this.chessman;
	         
	    }
}
