import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;

public class Reminder {
	Timer timer;
	int nextHour;
	int nextMinute;

	public Reminder(int seconds) {
		timer = new Timer();
	}

	public static void main(String args[]) throws IOException {
		
		SimpleDigitalClock digitalClock = new SimpleDigitalClock();
		BellInterface bellInterface = new BellInterface();
		
		// Get the Date corresponding to 11:01:00 pm today.
		String answer = "";

		String serverAddress = JOptionPane
				.showInputDialog("Enter IP Address of a machine that is\n"
						+ "running the date service on port 9090:");

		Socket s = new Socket(serverAddress, 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		answer = input.readLine();
		// JOptionPane.showMessageDialog(null, answer);

		if (answer.length() == 0) {
			System.out.println("IM DYING!!!!");
			System.exit(1);
		}
		String[] answerSplit = answer.split(",");
		int[] times = new int[answerSplit.length];
		for (int i = 0; i < answerSplit.length; i++) {
			times[i] = Integer.parseInt(answerSplit[i]);
		}
		System.out.println(Arrays.toString(times));

		// int[] times = { 810, 815, 910, 915, 1010, 1025, 1035, 1125, 1135,
		// 1225, 1300, 1305, 1400, 1405, 1500 };
		// int[] times = { 959, 958 };

		for (int i = 0; i < times.length; i++) {
			int hour = (int) (times[i] / 100.0);
			int minute = (int) (times[i] % 100);

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, 0);
			Date time = calendar.getTime();
			Date currentDate = new Date();
			if (currentDate.compareTo(time) < 0) {
				// new Reminder(5);
				Timer myTimer = new Timer();
				RemindTask rt = new RemindTask();
				myTimer.schedule(rt, time);
				System.out.println("Task scheduled.%n");
			} else {
				System.out.println("Time has already passed");
			}
		}
	}

}