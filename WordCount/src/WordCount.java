import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
public class WordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String apath = WordCount.class.getClassLoader().getResource("a.txt").getPath();
		String bpath = WordCount.class.getClassLoader().getResource("b.txt").getPath();
		//String cpath = WordCount.class.getClassLoader().getResource("c.txt").getPath();
		
		Map<String, Integer> aWordCount = new HashMap<String, Integer>();//a文件的总共的词汇表
		Map<String, Integer> bWordCount = new HashMap<String, Integer>();//b文件的总共的词汇表
		Map<String, Integer> joinWordCount = new HashMap<String, Integer>();//a、b总共的词汇表（不重复）
		Map<String, Integer> mixWordCount = new HashMap<String, Integer>();//a、b的交叉词汇表
		int joinCount = 0;
		int mixCount = 0;
		int aCount = 0;//不重复单词个数
		int bCount = 0;
		int aNotB = 0;//wd∈A 且wd ∉B的单词:aNotB=aCount-mixCount
		int bNotA = 0;//wd∈B且 wd∉A的单词占B文件的百分比:bNotA=bCount-mixCount
		
		try {
			BufferedReader abr = new BufferedReader(new FileReader(apath));
			BufferedReader bbr = new BufferedReader(new FileReader(bpath));
			String as;
			String bs;
			StringBuffer asb = new StringBuffer();
			StringBuffer bsb = new StringBuffer();
			while((as = abr.readLine())!=null) {
				//System.out.println("as="+as);
				asb.append(as);
				asb.append(" ");//否则会连在一起！！
			}
			while((bs = bbr.readLine())!=null) {
				//System.out.println("bs="+bs);
				bsb.append(bs);
				bsb.append(" ");
			}
			StringTokenizer ast = new StringTokenizer(asb.toString()," ,.?!:;\"\'\n");//空格 ? . : "" '' \n去分割
			StringTokenizer bst = new StringTokenizer(bsb.toString()," ,?.!:\"\"''\r\n");
			while(ast.hasMoreTokens()) {
				String word = ast.nextToken();
				//System.out.println("word="+word);
				int count;
				if(aWordCount.get(word)==null){
					count = 1;
					aCount++;
				}else {
					count = aWordCount.get(word).intValue() + 1;
				}
				aWordCount.put(word, count);
			}
			while(bst.hasMoreTokens()) {
				String word = bst.nextToken();
				int count;
				if(bWordCount.get(word)==null){
					count = 1;
					bCount++;
				}else {
					count = bWordCount.get(word).intValue() + 1;
				}
				bWordCount.put(word, count);
			}
			
			Iterator it = aWordCount.entrySet().iterator();
			//FileOutputStream fos = new FileOutputStream(cpath);
			//BufferedWriter bw = new BufferedWriter(new FileOutputStream(cpath));
			//System.out.println("aWordCount:");
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				joinWordCount.put((String)entry.getKey(), (Integer)entry.getValue());
				joinCount++;
				//bw.write(entry.getKey());
				//System.out.println(entry.getKey());
			}
			it = bWordCount.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				//System.out.println(entry.getKey());
				if(joinWordCount.get(entry.getKey())==null){
					joinWordCount.put((String)entry.getKey(), (Integer)entry.getValue());
					joinCount++;
				}else {//两文件单词的交集
					mixWordCount.put((String)(entry.getKey()),1);
					mixCount++;
				}
			}
			//两个文件总共词汇表打印
			it = joinWordCount.entrySet().iterator();
			System.out.println("两个文件并集词汇表打印:");
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				System.out.println(entry.getKey());
			}
			System.out.println("A、B文件并集单词总数joinCount=" + joinCount);
			//两个文件交集词汇表打印
			it = mixWordCount.entrySet().iterator();
			System.out.println("\n两个文件交集词汇表打印:");
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				System.out.println(entry.getKey());
			}
			System.out.println("A、B文件交集单词总数mixCount=" + mixCount);
			
			System.out.println("\nA文件单词总数（不重复）aCount="+aCount);
			System.out.println("B文件单词总数（不重复）bCount="+bCount);
			//wd∈A 且wd ∉B的单词占A文件的百分比
			System.out.println("wd∈A 且wd ∉B的单词占A文件的百分比" + (aCount-mixCount+0.0)/aCount);
			//wd∈B且 wd∉A的单词占B文件的百分比
			System.out.println("wd∈A 且wd ∉B的单词占A文件的百分比" + (bCount-mixCount+0.0)/bCount);
		}catch(FileNotFoundException e){
			System.out.println("File not found!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("File can not read!");
			e.printStackTrace();
		}

	}

}
