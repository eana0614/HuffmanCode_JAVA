package huffman;

import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {

		String filePath = "original text.txt";
		String encodingFile = "huffman encoding.txt";
		String decodingFile = "huffman decoding.txt";

		Scanner scanner = new Scanner(System.in);

		System.out.println("!] 1.Encoding \t 2.Decoding \t 0.Exit");
		int mode = scanner.nextInt();
		scanner.nextLine();

		if (mode == 0) {
			System.out.println("!] EXIT.");

		} else {
			while (mode > 0 && mode < 3) {

				HuffmanCode hc = new HuffmanCode();

				if (mode == 1) {
					
					hc.treeRead(filePath);

				} else if (mode == 2) {

				} else {
					System.err.println("Error] INPUT MODE NUMBER ERROR.");
					break;
				}

			}
		}

	}

}
