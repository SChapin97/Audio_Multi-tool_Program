package backend;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.io.File;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Driver {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		//Block of good wav files
		File gtbt = new File("Good_Times_Bad_Times.wav");
		File abitw = new File("Another_Brick_In_The_Wall.wav");
		File bor = new File("Baba_O'Riley.wav");
		File es = new File("Enter_Sandman.wav");
		File sha = new File("Sweet_Home_Alabama.wav");
		File ts = new File("Tom_Sawyer.wav");
		File rfj = new File("royaltyFreeJam.wav");
		
		//One mp3 file
		File fs = new File("Fortunate_Son.mp3");
		
		//Block for File Audio Handling tests
		/*
		//FileAudioHandling fah = new FileAudioHandling(gtbt, 0f);
		FileAudioHandling fah = new FileAudioHandling(ts, 0f);
		//FileAudioHandling fah = new FileAudioHandling(fs, 0f);
		fah.start();
		/*
		for (int i = 0; i < 7999999; i++) {
			System.out.print(i);
			if (i % 10 == 0) {
				System.out.println("");
			}//end of if statement
		}//end of for loop
		
		fah.stop();
		*/
		
		//Block for Normalizer tests
		
		Queue<File> fileInputQueue = new LinkedList<File>();
		fileInputQueue.add(gtbt);
		fileInputQueue.add(rfj);
		fileInputQueue.add(ts);
		fileInputQueue.add(sha);
		fileInputQueue.add(abitw);
		fileInputQueue.add(bor);
		fileInputQueue.add(es);
		fileInputQueue.add(fs);
		
		Normalizer nmz = new Normalizer(fileInputQueue);
		//Normalizer nmz = new Normalizer(fileInputQueue, "C:\\Users\\recon\\Downloads\\Normalizer");
		
	}//end of main method2

}//end of Driver class
