package com.madhouse.app.repository;
import java.util.*;

public class camp
{
	public int campId;
	public int positionId;
	public int Ver;
	public int ip;
	
	//private List positionId = new ArrayList();
	int count;
	
	
	public static int tag;
	static int _DATA_REQUEST     = 0;
	static int _DATA_IMPRESSION  = 1;
	static int _DATA_CVIMP       = 2;
	static int _DATA_TRACK       = 3 ;
	static int _DATA_TP          = 4;
	static int _DATA_FCAPSKIP    = 5;
	static int _DATA_SITE        = 6;
	static int _DATA_CLICK       = 11;
	static int _DATA_SCLICK      = 12;
	static int _DATA_CLOSE       = 13;
	static int _DATA_NCLICK      = 14;
	static int _DATA_DCLICK      = 15;
	static int _DATA_TCLICK      = 16;
	static int _DATA_TPCLICK     = 17;
	
	//log position
	public static int _OFFSET_CAMPID = 0;
	public static int _OFFSET_ADPOS  = 1;
	public static int _OFFSET_OPID   = 1;
	static int _OFFSET_NWID   = 2; //network id ?
	static int _OFFSET_OPPOS  = 2; //??
	static int _OFFSET_DATAP  = 3;
	static int _OFFSET_UI     = 2;
	static int _OFFSET_UAID   = 5;
	static int _OFFSET_GW     = 6;
	static int _OFFSET_STAG   = 7;
	static int _OFFSET_TS     = 8;
	static int _OFFSET_CS     = 11;
	
	
	public camp(String ilist)
	{
		
	}
	
	public int getCamp()
	{
		int i = 0;
		
		
		System.out.println(camp._DATA_CLICK);
		return i;
	}
}