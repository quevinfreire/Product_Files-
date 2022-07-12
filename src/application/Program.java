package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("File in: ");
		String in = sc.nextLine();
		
		File path = new File(in); 
		boolean sucess = new File(path.getParent() + "\\out").mkdir();
		System.out.println("Directory created successfully: " + sucess);
		
		String out = (path.getParent() + "\\out\\summary.csv");
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(in))) {
			
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				list.add(new Product (name, price, quantity));
				
				line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
				
				for(Product l: list) {
					bw.write(l.getName() + ", " + String.format("%.2f",l.total()));
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 
		catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		
		for (Product l: list) {
			System.out.println(l);
		}
		
		sc.close();
		
	}

}
