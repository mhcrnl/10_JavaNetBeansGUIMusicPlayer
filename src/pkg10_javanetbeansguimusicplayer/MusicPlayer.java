/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10_javanetbeansguimusicplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayer extends JPanel implements ActionListener{
	JFrame window = new JFrame("Music Player");
	JLabel info = new JLabel("JAVA_PROJECT(±è±èŒÛÂ÷)");
	JButton addButton = new JButton("Add Music");
	JButton playButton = new JButton("Play");
	JButton stopButton = new JButton("Stop");

	JComboBox list = new JComboBox();
	JFileChooser browser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Sound", "wav");
	int returnValue;
	String[] musics = new String[10];
	int index = 0;
	File selectedFile;
	File sound;
	AudioInputStream ais;
	Clip clip;
	
	MusicPlayer()
	{
		this.setBackground(Color.WHITE);
		window.add(this);
		
		addButton.setSize(500,500);
		playButton.setSize(500,500);
		stopButton.setSize(120,120);
		
		addButton.addActionListener(this);
		playButton.addActionListener(this);
		stopButton.addActionListener(this);

		
		JPanel panel;
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		info.setFont(new Font("",Font.ITALIC,12));
		
		window.add(info,BorderLayout.PAGE_END);
		
		panel.add(playButton);
		panel.add(stopButton);
		panel.add(addButton);
		
		add(panel);
		
		window.add(list,BorderLayout.PAGE_START);
		
		browser.setFileFilter(filter);

		window.setSize(400,200);
		window.setLocation(100, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==addButton)
		{
			returnValue = browser.showOpenDialog(window);
			
			if(returnValue == browser.APPROVE_OPTION)
			{
				selectedFile = browser.getSelectedFile();
				musics[index] = selectedFile.toString();
				list.addItem("Song - " + index+"-"+musics[index]);
				index++;
			}
		}
	
		else if(ae.getSource()==playButton)
		{
			
			
			try{
				for(int a=0; a<100; a++){
					
			
				if(list.getSelectedIndex()==a)
				{
					sound = new File(musics[list.getSelectedIndex()]);
					ais = AudioSystem.getAudioInputStream(sound);
					clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
					}
				
				else if(list.getSelectedIndex()==a+1)
				{
					clip.stop();
					}
				
			}
		
				
		}
	
			
		
				
			catch(Exception e){JOptionPane.showMessageDialog(null, e);}
		
	}
		
		else if(ae.getSource()==stopButton)
		{
			clip.stop();
		}

	}
}
