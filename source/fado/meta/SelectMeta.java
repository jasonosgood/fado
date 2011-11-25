package fado.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 
	SelectMeta 
{
	private String _name = null;
	
	public void setName( String name )
	{
		_name = name;
	}
	
	public String getName()
	{
		return _name;
	}
	
	private String _package;
	public void setPackage( String pkg )
	{
		_package = pkg;
	}
	
	public String getPackage()
	{
		return _package;
	}
	
	/** Temporary columns are what's found in the parsed SQL command. When found,
	 * they're moved to the final columns list.
	 */
	private ArrayList<Column> _tempColumns = new ArrayList<Column>();
	
	public void tempColumn( Column column )
	{
		if( column == null )
		{
			throw new NullPointerException( "column" );
		}
		_tempColumns.add( column );
	}
	
	public List<Column> getTempColumns()
	{
		return _tempColumns;
	}
	
	private ArrayList<Column> _columns = new ArrayList<Column>();
	private HashMap<String,Column> _columnMap = new HashMap<String,Column>();
	
	public void addFinalColumn( Column column )
	{
		if( column == null )
		{
			throw new NullPointerException( "column" );
		}
		_columns.add( column );
		String key = column.getName();
		_columnMap.put( key, column );
	}
	
	public List<Column> getFinalColumns()
	{
		return _columns;
	}
	
	public Column getFinalColumn( String key )
	{
		return _columnMap.get( key );
	}
	
	private ArrayList<Table> _tables = new ArrayList<Table>(); 
	
	public void table( Table table )
	{
		if( table == null )
		{
			throw new NullPointerException( "table" );
		}
		_tables.add( table );
	}
	
	public List<Table> getTables()
	{
		return _tables;
	}
	
	public Table getTable( String name )
		throws TableNotFoundException
	{
		if( name == null ) return null;
		List<Table> tables = getTables();
		
		for( Table table : tables )
		{
			if( name.equals( table.getAlias() ) || name.equals( table.getName() ))
			{
				return table;
			}
		}
		
		throw new TableNotFoundException( "table alias not found: " + name );
	}
	
	private ArrayList<Condition> _conditions = new ArrayList<Condition>();
	
	public void comparison( Condition condition )
	{
		if( condition == null )
		{
			throw new NullPointerException( "condition" );
		}
		_conditions.add( condition );
	}
	
	public List<Condition> getConditions()
	{
		return _conditions;
	}
	
	private String _rewrite = "n/a";

	public void setRewrite( String rewrite )
	{
		if( rewrite == null )
		{
			throw new NullPointerException( "rewrite" );
		}
		_rewrite = rewrite;
	}
	
	public String getRewrite()
	{
		return _rewrite;
	}
	
	private String _originalFileName = null;
	
	public void setOriginalFileName( String originalFileName )
	{
		_originalFileName = originalFileName;
	}
	
	public String getOriginalFileName()
	{
		return _originalFileName;
	}
	
	private List<String> _originalSQL = null;
	
	public void setOriginalSQL( List<String> originalSQL )
	{
		_originalSQL = originalSQL;
	}
	
	public List<String> getOriginalSQL()
	{
		return _originalSQL;
	}
}
