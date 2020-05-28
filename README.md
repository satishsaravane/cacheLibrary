# Cache Library Using Java

Light-weight implementation in-memory key-value cache mechanism in Java.

# Criteria

- Store data in memory
- Allow putting object by key for some amount of time
- The cache should remove expired objects
- Thread-safe


# Implementation:

ConcurrentHashMap has been used to make it thread-safe.
SoftReference<Object> as a map value used because soft reference guarantees that referenced object will be removed in case of lack of memory before OutOfMemory will be thrown.
In the constructor, a daemon thread that scans every 5 seconds and cleans up expired objects.
DelayQueue allows adding elements to the queue with delay in our program expiration period, so we can schedule removing of expired objects.

# Usage:
We can use it to store some long-running queries from DB or frequently used data.

# Limitations:

It’s a simple example of in-memory cache,
Distributed cache solutions will more efficient

