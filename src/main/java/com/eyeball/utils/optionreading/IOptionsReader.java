package com.eyeball.utils.optionreading;

/**
 * 
 * An interface for options reading. Implement this and provide your own means
 * of reading and parsing.
 * 
 * @author Eyeball
 *
 */
public interface IOptionsReader {
	/**
	 * Puts a coment into the file
	 * 
	 * @param text The comment
	 */

	public void comment(String text);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The boolean read
	 */
	public boolean readBoolean(String varName, boolean defaultANS);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The float read
	 */
	public float readFloat(String varName, float defaultANS);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The int read
	 */
	public int readInt(String varName, int defaultANS);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The long read
	 */
	public long readLong(String varName, long defaultANS);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The String read
	 */
	public String readString(String varName, String defaultANS);

	/**
	 * 
	 * @param varName
	 *            The name of the option
	 * @return The String array read
	 */
	public String[] readStringArray(String varName, String[] defaultANS);

}
