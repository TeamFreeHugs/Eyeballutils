package com.eyeball.utils.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

import com.eyeball.utils.advt.AdvancedDataWriter;
import com.eyeball.utils.advt.objects.CrateObject;
import com.eyeball.utils.advt.objects.StringArray;
import com.eyeball.utils.advt.objects.StringObject;

public class TestAdvancedDataWriter {

	/**
	 * Tree:<br>
	 * -->AdvancedDataWriter ADW<br>
	 * ---->CrateObject crate1<br>
	 * ---->StringObject helloMessage<br>
	 * ---->CrateObject crate2<br>
	 * ------>StringArray info<br>
	 * -------->StringObject name<br>
	 * 
	 */
	public static void main(String[] args) throws IOException {
		AdvancedDataWriter ADW = new AdvancedDataWriter(new File(
				System.getProperty("user.home") + "/ADVTEST"));
		//All the data. However, it is not necessary to use a CrateObject called Data.
		CrateObject crate1 = new CrateObject("Data");
		//The Hello World message.
		StringObject helloMessage = new StringObject("helloMessage");
		//Setting the text.
		helloMessage.setText("Hello World From ADW");
		//A CrateObject containg User Info
		CrateObject crate2 = new CrateObject("User Info");
		StringArray info = new StringArray("Personal Details");
		StringObject name = new StringObject("Name");
		name.setText("Eyeballcode");
		info.addObject(name);
		crate2.addObject(info);
		crate1.addObject(helloMessage);
		crate1.addObject(crate2);
		ADW.addObject(crate1);
		ADW.flush();

		InflaterInputStream in = new InflaterInputStream(new FileInputStream(
				new File(System.getProperty("user.home") + "/ADVTEST.ADV")));

		ArrayList<Integer> ints = new ArrayList<Integer>();

		int read = in.read();
		while (read != -1) {
			ints.add(read);
			read = in.read();
		}
		in.close();

		for (int i : ints) {
			System.out.print(Character.valueOf((char) i));
		}

	}

}
