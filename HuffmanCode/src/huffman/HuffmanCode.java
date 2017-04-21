package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HuffmanCode {
	Node[] tree;
	
	public HuffmanCode(){
		this.tree = new Node[27];
		
		initTree();
	}

	private void initTree() {
		
		tree[0].c = ' ';
		tree[0].freq = 0;
		tree[0].left = null;
		tree[0].right = null;
		tree[0].path = "";
		
		for(int i=1; i<tree.length; i++){
			tree[i].c = (char) ('a'+i-1);
			tree[i].freq = 0;
			tree[i].left = null;
			tree[i].right = null;
			tree[i].path = "";
		}
	}


	public void treeRead(String filePath) throws IOException {
		
		File readFile = new File(filePath);
		FileInputStream fis = new FileInputStream(readFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String temp;
		while((temp = br.readLine()) != null){
			int length = temp.length();
			
			for(int i=0; i<length; i++){
				char t = temp.charAt(i);
				
				if(t == ' '){
					tree[0].freq++;
				}else{
					tree[t-'a'+1].freq++;
				}
			}
		}
		
		br.close();
		isr.close();
		fis.close();
	}

}
