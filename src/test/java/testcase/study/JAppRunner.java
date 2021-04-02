package testcase.study;

import javax.swing.*;

public class JAppRunner {
	public static boolean isTestMyJunit;

	public JAppRunner( ) {

	}

	boolean _isPackFrame = false;



	public JAppRunner(boolean packFrame  ) {
		set_packFrame(packFrame);

	}

	<T extends JFrame> void showJFrame(T jf) {

		try {

			if (is_packFrame()) {
				jf.pack();
			} else {
				jf.pack();
				jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			jf.setVisible(true);
			jf.setExtendedState(JFrame.MAXIMIZED_BOTH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean is_packFrame() {
		return _isPackFrame;
	}

	public void set_packFrame(boolean frame) {
		_isPackFrame = frame;
	}


}

// Dimension screenSize =
// Toolkit.getDefaultToolkit().getScreenSize();
// Dimension frameSize = myJFrame.getSize();
// if (frameSize.height > screenSize.height)
// frameSize.height = screenSize.height;
// if (frameSize.width > screenSize.width)
// frameSize.width = screenSize.width;
// myJFrame.setLocation((screenSize.width - frameSize.width) / 2,
// (screenSize.height - frameSize.height) / 2);
