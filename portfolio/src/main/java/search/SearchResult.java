package search;

import java.util.HashMap;
import java.util.LinkedList;

public class SearchResult
{
  private int total;
  private LinkedList<HashMap> resultList;

  public int getTotal()
  {
    return this.total; }

  public void setTotal(int total) {
    this.total = total; }

  public LinkedList<HashMap> getResultList() {
    return this.resultList; }

  public void setResultList(LinkedList<HashMap> resultList) {
    this.resultList = resultList;
  }
}