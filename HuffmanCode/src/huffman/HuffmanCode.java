package huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HuffmanCode {
	Node[] tree;
	Node[] q;

	Node[] table = new Node[27];;

	int treeLength = 27;;

	public HuffmanCode() {
		this.tree = new Node[treeLength];
		initTree();
	}

	private void initTree() {

		for (int i = 0; i < treeLength; i++) {

			tree[i] = new Node();
			table[i] = new Node();

			if (i == 0) {
				tree[0].c = ' ';
				tree[0].freq = 0;
				tree[0].left = null;
				tree[0].right = null;
				tree[0].path = "";
			} else {
				tree[i].c = (char) ('a' + i - 1);
				tree[i].freq = 0;
				tree[i].left = null;
				tree[i].right = null;
				tree[i].path = "";
			}
		}

		sortTree();
	}

	private void sortTree() {
		Node temp;

		for (int i = 1; i < tree.length; i++) {
			temp = tree[i];
			int j = i - 1;

			while (j >= 0 && tree[j].freq > temp.freq) {
				tree[j + 1] = tree[j];
				j = j - 1;
			}

			tree[j + 1] = temp;
		}

	}

	public void treeRead(String filePath) throws IOException {

		File readFile = new File(filePath);
		FileInputStream fis = new FileInputStream(readFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String temp;
		while ((temp = br.readLine()) != null) {
			int length = temp.length();

			for (int i = 0; i < length; i++) {
				char t = temp.charAt(i);

				if (t == ' ') {
					tree[0].freq++;
				} else {
					tree[t - 'a' + 1].freq++;
				}
			}
		}

		br.close();
		isr.close();
		fis.close();

		sortTree();
	}

	public Node huffman() {
		int n = treeLength;

		q = tree;

		for (int i = 0; i < n; i++) {
			Node z = new Node();
			Node x = extractMin();
			z.left = x;
			Node y = extractMin();
			z.right = y;
			z.freq = x.freq + y.freq;
			z.path = "";
			insert(z);
		}

		Node root = extractMin();
		root = root.left;

		return root;
	}

	private void insert(Node z) {
		int i;

		for (i = 0; i < treeLength; i++) {
			if (q[i].freq > z.freq) {
				break;
			}
		}

		for (int j = treeLength + 1; j > i; j--) {
			q[j] = q[j - 1];
		}

		q[i] = z;
		treeLength++;
	}

	private Node extractMin() {
		Node temp = q[0];

		for (int i = 0; i < treeLength - 1; i++) {
			q[i] = q[i + 1];
		}

		treeLength--;

		return temp;
	}

	public void createTable(Node t) {
		String str = "";
		char temp = t.c;

		if (temp >= 'a' && temp <= 'z') {
			table[temp - 'a' + 1].c = t.c;
			table[temp - 'a' + 1].path = t.path;
		} else if (temp == ' ') {
			table[0].c = ' ';
			table[0].path = t.path;
		}

		if (t.left != null) {
			str = t.path + "0";
			t.left.path = str;
			createTable(t.left);
		}

		if (t.right != null) {
			str = t.path + "1";
			t.right.path = str;
			createTable(t.right);
		}

	}

	public void writeTableTxt(String tableFile) throws IOException {

		File writeFile = new File(tableFile);
		FileOutputStream fos = new FileOutputStream(writeFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);

		for (int i = 0; i < table.length; i++) {
			String temp = table[i].c + "," + table[i].path;
			bw.write(temp);
			bw.newLine();
		}

		bw.close();
		osw.close();
		fos.close();

	}

	public void encodingHuffman(String original, String encoding) throws IOException {

		File read = new File(original);
		FileInputStream fis = new FileInputStream(read);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		File write = new File(encoding);
		FileOutputStream fos = new FileOutputStream(write);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);

		String temp;
		while ((temp = br.readLine()) != null) {
			int length = temp.length();

			for (int i = 0; i < length; i++) {
				char t = temp.charAt(i);

				if (t == ' ') {
					bw.write(table[0].path);
				} else {
					bw.write(table[t - 'a' + 1].path);
				}
			}
		}

		bw.close();
		osw.close();
		fos.close();
		br.close();
		isr.close();
		fis.close();

	}

	public void decodingHuffman(String encodingFile, String decodingFile) throws IOException {

		File encoding = new File(encodingFile);
		FileInputStream fisE = new FileInputStream(encoding);
		InputStreamReader isrE = new InputStreamReader(fisE);
		BufferedReader brEncod = new BufferedReader(isrE);

		File write = new File(decodingFile);
		FileOutputStream fos = new FileOutputStream(write);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);

		String temp;
		String buf = "";

		while ((temp = brEncod.readLine()) != null) {
			for (int i = 0; i < temp.length(); i++) {
				char c = temp.charAt(i);
				buf += c;

				if (buf.length() >= 3) {
					for (int j = 0; j < 27; j++) {
						
						if (buf.equals(table[j].path)) {
							bw.write("" + table[j].c);
							buf = "";
							break;
						}
					}
				}
			}
		}

		bw.close();
		osw.close();
		fos.close();
		brEncod.close();
		isrE.close();
		fisE.close();

	}

}
