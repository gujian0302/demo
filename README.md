#NIO 编程思想

<h5 style="color:red;">注意事项</h5>
* 方法执行中不能有阻塞操作，类似于DB,HTTP同步操作，睡眠等，由于阻塞之后，线程被占用，后续的请求就会被堆积，造成线程和内存的耗尽，解决方案。<br/>
  1. HTTP请求使用异步操作<br />
  2. 使用独立的线程池来处理阻塞请求，不影响主业务的执行<br />
  3. 关系型数据库操作必须在OIO中执行<br />
  4. The fork join pool based dispatcher in Akka then attempts to compensate for this blocking by adding more threads to the pool (default-akka.actor.default-dispatcher 18,19,20,...).(FORKJOIN POOL 会在阻塞时，添加线程到线程池)<br />

<h3>The non-exhaustive list of adequate solutions to the <strong style="color:red;"><em>“blocking problem”</em></strong> includes the following suggestions:</h3>

<li>Do the blocking call within an actor (or a set of actors managed by a router router, making sure to configure a thread pool which is either dedicated for this purpose or sufficiently sized.</li>
<li>Do the blocking call within a Future, ensuring an upper bound on the number of such calls at any point in time (submitting an unbounded number of tasks of this nature will exhaust your memory or thread limits).</li>
<li>Do the blocking call within a Future, providing a thread pool with an upper limit on the number of threads which is appropriate for the hardware on which the application runs, as explained in detail in this section.</li>
<li>Dedicate a single thread to manage a set of blocking resources (e.g. a NIO selector driving multiple channels) and dispatch events as they occur as actor messages.</li>
