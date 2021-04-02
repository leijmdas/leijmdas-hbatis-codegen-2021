package testcase.study;

import javax.swing.*;

public class JMainRunner {

	public static void main(String args[]) throws InterruptedException {


	}
}


class JAppletRunner {
	JAppletRunner() {
	}

	void run() {
		JFrame jf = new JFrame();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setVisible(true);
	}
}

// if (TestMySql.class.isAnnotationPresent(JTestClass.class)) {
// System.out.println(TestMySql.class.getAnnotation(JTestClass.class));
// }
// getLogger().warn("warn???");
// getLogger().info("info");
// System.exit(0);

// Date enddate = new Date();
// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
// System.out.println("start date- end date:\t "
// + df.format(startdate) + "\t" + df.format(enddate));
