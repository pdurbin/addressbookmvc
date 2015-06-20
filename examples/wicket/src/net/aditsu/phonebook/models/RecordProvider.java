package net.aditsu.phonebook.models;

import java.util.Iterator;

import net.aditsu.depeche.DynamicQuery;
import net.aditsu.depeche.Record;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class RecordProvider extends SortableDataProvider<Record, String> {
	private static final long serialVersionUID = 1L;

	private final DynamicQuery query;

	public RecordProvider(final DynamicQuery query) {
		this.query = query;
	}
	
	@Override
	public Iterator<Record> iterator(final long first, final long count) {
		DynamicQuery q = query;
		final SortParam<String> s = getSort();
		if (s != null) {
			final String p = s.getProperty();
			q = s.isAscending() ? q.order(p) : q.desc(p);
		}
		q = q.limit(count).offset(first);
		return q.iterator();
	}

	@Override
	public IModel<Record> model(final Record rec) {
		return Model.of(rec);
	}

	@Override
	public long size() {
		return query.longCount();
	}
}
