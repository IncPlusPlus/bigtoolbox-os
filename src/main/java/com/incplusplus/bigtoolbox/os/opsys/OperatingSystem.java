package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.UnsupportedOSException;
import com.incplusplus.bigtoolbox.os.opsys.linux.Linux;
import com.incplusplus.bigtoolbox.os.opsys.mac.Mac;
import com.incplusplus.bigtoolbox.os.opsys.other.Unknown;
import com.incplusplus.bigtoolbox.os.opsys.windows.Windows;
import org.apache.commons.lang3.SystemUtils;


//TODO Implement Comparable
//TODO Add documentation for all classes and all methods in the OS package
public abstract class OperatingSystem
{
	public enum OSFamily
	{
		Windows, Linux, Mac
	}

	public static OperatingSystem getOS()
	{
		if(SystemUtils.IS_OS_WINDOWS)
		{
			return Windows.getInstance();
		}
		else if(SystemUtils.IS_OS_LINUX)
		{
			return Linux.getInstance();
		}
		else if(SystemUtils.IS_OS_MAC)
		{
			return Mac.getInstance();
		}
		throw new UnsupportedOSException();
	}

	public abstract String getVersion();

	public final OperatingSystem getSkeleton(OSFamily osFamily, String majorVersion, String minorVersion)
	{
		return getSkeleton(osFamily,majorVersion,minorVersion,"");
	}

	public final OperatingSystem getSkeleton(OSFamily osFamily, String majorVersion, String minorVersion, String buildVersion)
	{
		if(osFamily.equals(OSFamily.Windows))
		{
			return Windows.getSkeletonOS(majorVersion, minorVersion, buildVersion);
		}
		if(osFamily.equals(OSFamily.Linux))
		{
			throw new UnsupportedOSException();
		}
		if(osFamily.equals(OSFamily.Mac))
		{
			throw new UnsupportedOSException();
		}
		throw new UnsupportedOSException();
	}

	/*
	 * The contract for Windows, Linux, and Mac superclasses is that they must have a
	 * static getSkeletonOS method that returns a subclass with the proper versioning.
	 */
	//abstract OperatingSystem getSkeletonOS(String majorVersion, String minorVersion, String buildVersion);
}