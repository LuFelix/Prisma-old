package online.lucianofelix.util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldNumeros extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2813882798424103684L;

	public JTextFieldNumeros() {
		setDocument(new TeclasPermitidas());
	}
	private class TeclasPermitidas extends PlainDocument {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1330528028457719482L;
		@Override
		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			super.insertString(offset, str.replaceAll("[^0-9|^,|.|null]", ""),
					attr);

		}
		@SuppressWarnings("unused")
		public void replace(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			super.insertString(offset, str.replaceAll("[^0-9|^,|.|null]", ""),
					attr);

		}

	}
}
