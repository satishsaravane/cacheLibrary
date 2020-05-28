package com.memory.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.memory.CacheImpl;
import org.junit.Test;

public class CacheTest {

  @Test
  public void testCacheStartsEmpty() {
    CacheImpl cache = new CacheImpl();
    assertEquals(cache.size(), 0);
  }


  @Test
  public void testAddRemoveObjects() {
    CacheImpl cache = new CacheImpl();

    cache.add("eBay", "eBay", 15000);
    cache.add("Paypal", "Paypal", 1000);
    cache.add("Google", "Google", 2500);
    cache.add("Microsoft", "Microsoft", 2400);
    cache.add("IBM", "IBM", 4566);
    cache.add("Facebook", "Facebook", 5555);
    assertEquals(cache.size(),6);
    cache.remove("IBM");
    cache.add("Twitter", "Twitter", 1500);
    cache.add("SAP", "SAP", 2500);
    assertEquals(cache.size(),7);
  }

  @Test
  public void testExpiredCacheObjects() throws InterruptedException {

    CacheImpl cache = new CacheImpl();
    cache.add("eBay", "eBay", 1400);
    cache.add("Paypal", "Paypal", 2500);
    Thread.sleep(3000);
    assertEquals(cache.size(),0);
  }

  @Test
  public void testObjectsCleanupTime() throws InterruptedException {
    int size = 500000;

    CacheImpl cache = new CacheImpl();
    for (int i = 0; i < size; i++) {
      String value = Integer.toString(i);
      cache.add(value, value, size);
    }
    Thread.sleep(200);
    long start = System.currentTimeMillis();
    cache.clear();
    assertEquals(cache.size(),0);
    double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
    assertTrue(finish>0);
  }
}
