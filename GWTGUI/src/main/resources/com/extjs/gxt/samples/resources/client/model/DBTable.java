/*
 * Ext GWT 2.2.0 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.resources.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class DBTable extends BaseTreeModel {

  public DBTable() {

  }

  public DBTable(String name) {
    set("name", name);
  }

  public DBTable(String name, String author, String genre) {
    set("name", name);
    set("author", author);
    set("genre", genre);
  }

 public DBTable(String name,String displayedName, String database,  String id, String location) {
    set("name", name);
    set("displayedName", displayedName);
    set("database", database);
    set("id", id);
    set("location", location);
  }

  public String getName() {
    return (String) get("name");
  }

  public String getDatabase() {
    return (String) get("database");
  }

  public String getDisplayedName() {
    return (String) get("displayedName");
  }

  public String getId() {
    return (String) get("id");
  }
  public String getLocation() {
    return (String) get("location");
  }
  public String toString() {
    return getName();
  }
}
