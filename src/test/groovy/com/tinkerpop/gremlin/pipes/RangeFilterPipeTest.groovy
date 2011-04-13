package com.tinkerpop.gremlin.pipes

import com.tinkerpop.blueprints.pgm.Graph
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory
import com.tinkerpop.gremlin.Gremlin
import com.tinkerpop.pipes.Pipe
import junit.framework.TestCase

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
class RangeFilterPipeTest extends TestCase {

    public void testIsPipe() throws Exception {
        Gremlin.load();
        Graph g = TinkerGraphFactory.createTinkerGraph();
        assertTrue(g.V[0..10] instanceof Pipe)
        assertTrue(g.V.outE[0..<10] instanceof Pipe)
        assertTrue(g.V.outE.inV[1] instanceof Pipe)
    }

    public void testBasicRange() {
        Gremlin.load();
        Graph g = TinkerGraphFactory.createTinkerGraph();
        def results = []
        g.v(1).outE[0..2] >> results
        assertEquals(results.size(), 2)
        assertEquals(results[0], g.v(1).outE[0] >> 1)
        assertEquals(results[1], g.v(1).outE[1] >> 1)
    }
}
