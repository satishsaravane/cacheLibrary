package com.memory;

public class CacheMain {

  public static void main(String args[]) {
    CacheMain cacheMain = new CacheMain();
    try {
      System.out.println("\n\n==========AddRemoveCache ==========");
      cacheMain.testAddRemoveObjects();
      System.out.println("\n\n==========ExpiredCacheObjects ==========");
      cacheMain.testExpiredCacheObjects();
      System.out.println("\n\n========== ObjectsCleanupTime ==========");
      cacheMain.testObjectsCleanupTime();
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }


  public void testAddRemoveObjects() {
    CacheImpl cache = new CacheImpl();
    cache.add("eBay", "eBay", 15000);
    cache.add("Paypal", "Paypal", 1000);
    cache.add("Google", "Google", 2500);
    cache.add("Microsoft", "Microsoft", 2400);
    cache.add("IBM", "IBM", 4566);
    cache.add("Facebook", "Facebook", 5555);
    System.out.println("6 Cache Object Added.. cache.size(): " + cache.size());
    cache.remove("IBM");
    System.out.println("One object removed.. cache.size(): " + cache.size());
    cache.add("Twitter", "Twitter", 1500);
    cache.add("SAP", "SAP", 2500);
    System.out.println("Two objects Added but reached maxItems.. cache.size(): " + cache.size());
  }

  public void testExpiredCacheObjects() throws InterruptedException {
    CacheImpl cache = new CacheImpl();
    cache.add("eBay", "eBay", 1400);
    cache.add("Paypal", "Paypal", 2500);
    Thread.sleep(3000);
    System.out
        .println("Two objects are added but reached timeToLive. cache.size(): " + cache.size());

  }

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
    double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
    System.out.println("Cleanup times for " + size + " objects are " + finish + " s");
  }
}
