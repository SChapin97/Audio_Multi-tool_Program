package backend;

import java.util.LinkedList;
import java.util.Queue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/* This is the Normalizer class, which handles the input, output, and normalization of 16-bit PCM signed wave files.
 * Normalization in this case is defined as the manipulation of an audio file as to reduce the percieved loudness of
 * the file's audio peak and increased loudness of the audio's troughs in its waveform visualization.
 * 
 * Currently, the normalization portion of this class is not implemented, as I am unfamiliar with JNI library usage,
 * but the class currently does create exported files that are created through byte array allocation from the given
 * files from the user.
 */

//Oracle Doc: https://docs.oracle.com/javase/7/docs/technotes/guides/sound/programmer_guide/chapter6.html

public class Normalizer {

	private Queue<File> fileInputQueue;
	private Queue<File> fileOutputQueue;

	//This default constructor will output all files to a new folder in the current directory.
	public Normalizer (Queue<File> fileInputQueue) throws IOException { 
		this.fileInputQueue = (LinkedList<File>) fileInputQueue;
		fileOutputQueue = new LinkedList<File>();

		//Creating a new folder in the current user's Downloads folder.
		String userHome = System.getProperty("user.home");
		new File(userHome + "\\Downloads\\Normalizer").mkdirs();

		//This sets the directory to the newly created folder.
		String outputDirectory = userHome + "\\Downloads\\Normalizer\\";

		start(outputDirectory);
	}//end of Default Constructor

	//This default constructor will output all files into the folder of the given directory.
	public Normalizer (Queue<File> fileInputQueue, String outputDirectory) throws IOException {
		this.fileInputQueue = (LinkedList<File>) fileInputQueue;
		fileOutputQueue = new LinkedList<File>();

		//This line adds a backslash to the directory so that the normalizer can properly add files to the correct folder.
		outputDirectory += "\\";

		start(outputDirectory);
	}//end of Alternate Constructor

	//This method, once instantiated by the default constructor, will start the normalization process with a file queue.
	private void start(String outputDirectory) throws IOException {
		for (File inFile : fileInputQueue) {
			String dir = outputDirectory + inFile.getName();
			File outFile = new File(dir);
			fileToStream(inFile, outFile);
			fileOutputQueue.add(outFile);
			displayPosition();
		}//end of for loop

		System.out.println("All files in the queue have been processed.");
	}//end of start method

	//The fileToStream method intakes two files (input and output files), and translates the input file to a byte array.
	//The byte array is later written to an output file, but can be normalized before the output file is written with the buffer.
	private void fileToStream(File inFile, File outFile) throws IOException  {
		//Creating a buffer to store the input file's data to be later used by the ByteArrayInputStream.
		byte[] baisBuf = new byte[(int) inFile.length()];

		//Writing the file data to the buffer
		FileInputStream fis = new FileInputStream(inFile);
		fis.read(baisBuf);
		fis.close();

		//Creating ByteArray in/out streams and FileOutput stream (BAOS cannot write output to a file itself).
		ByteArrayInputStream bais = new ByteArrayInputStream(baisBuf);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileOutputStream fos = new FileOutputStream(outFile);

		//This loop takes the file data from the buffer and writes it to the BAOS so the file data can be normalized.
		int len = 0;
		while ((len = bais.read(baisBuf)) > 0) {
			normalize(bais);
			baos.write(baisBuf, 0, len);
		}//end of while loop

		//BAOS writes to the FileOutputStream, which writes the buffer data to a new output file.
		baos.writeTo(fos);
		bais.close();
		baos.close();
	}//end of fileToSream method

	//This method normalizes the input array data from a file, but is not currently implemented.
	//This method, when implemented, should also be ran on another thread to allow for the GUI to work while
	//the method runs.
	private void normalize(ByteArrayInputStream bais) {
		//This must be done by taking a byte array (likely from fileToStream) and converting the bytes to short,
		//and then manipulating that data.
	}//end of normalize method

	//This method displays the current number of files processed in "the" queue.
	public void displayPosition() {
		System.out.println("Queue: " + (fileOutputQueue.size()) + "/" + (fileInputQueue.size()) + " files processed.");
	}//end of displayPosition method

}//end of Normalizer class
