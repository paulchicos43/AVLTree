import static org.junit.Assert.fail;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AVLTests {
  AVL avl;

  @BeforeEach
  void setUp() {
    avl = new AVL();
  }

  @Test
  void test_001() {
    if (avl.root != null) {
      fail("Tree should be empty");
    }
  }


}
