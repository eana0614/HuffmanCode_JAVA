package huffman;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {

		String filePath = "original text.txt";
		String tableFile = "huffman table.txt";
		String encodingFile = "huffman encoding.txt";
		String decodingFile = "huffman decoding.txt";

		System.out.println("[ ENCODING\t▷\tDECODING\t▷\tEXIT ]");
		HuffmanCode hc = new HuffmanCode();

		System.out.println("!] Add Tree Element.");
		hc.treeRead(filePath);
					
		System.out.println("!] Run Huffman.");
		Node t = hc.huffman();
					
		System.out.println("!] Create Table Txt.");
		hc.createTable(t);
		hc.writeTableTxt(tableFile);
		
		System.out.println("!] Create Encoding Txt.");
		hc.encodingHuffman(filePath, encodingFile);
		
		System.out.println("!] Create Decoding Txt. ");
		hc.decodingHuffman(encodingFile, decodingFile);

		System.out.println("!] DONE. ");

	}

}
