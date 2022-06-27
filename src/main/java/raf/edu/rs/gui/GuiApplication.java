package raf.edu.rs.gui;

public class GuiApplication {
	public static void main(String[] args) {
		//SpringApplication.run(GuiApplication.class, args);
		MainFrame mainFrame = MainFrame.getInstance();
		mainFrame.setVisible(true);
	}
}