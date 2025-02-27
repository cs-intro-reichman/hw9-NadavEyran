/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node p = this.first;

		for (int i = 0; i < index ;i++ ) {
			p = p.next;
		}

		return p;
	}

	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node node = new Node(block);
		if (index == 0) {
			node.next = this.first;
			this.first = node;
			if (size == 0) {
				this.last = this.first;
			}
		} else {
			Node p = this.getNode(index - 1);
			node.next = p.next;
			p.next = node;
			if (index == size) {
				this.last = node;
			}
		}
		this.size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		this.add(size, block);
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
			this.add(0, block);
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index > size-1) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node p = this.first;
		for (int i = 0 ; i < index ; i++) {
			p = p.next;
		}

		return p.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		Node p = this.first;
		int indexBlock = 0;

		while (p != null) {
			if (p.block == block) {
				return indexBlock;
			}
			p = p.next;
			indexBlock++;
		}
		return -1;
	}

	/**
 * Removes the given node from this list.
 *
 * @param node the node that will be removed from this list
 */
public void remove(Node node) {
    if (node == null) {
    	throw new NullPointerException();
    } 

    // Case 1: Removing the first node
    if (this.first == node) {
        this.first = this.first.next;
        if (this.size == 1) { // If the list becomes empty
            this.last = null;
        }
        this.size--;
        return;
    }

    // Traverse the list to find the preceding node
    Node p = this.first;
    while (p != null && p.next != null) {
        if (p.next == node) { // Found the preceding node
            p.next = node.next; // Bypass the node to remove it
            if (node == this.last) { // If it's the last node
                this.last = p;
            }
            this.size--;
            return;
        }
        p = p.next;
    }

    // If we reach here, the node was not found in the list
}


	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
	    if (index < 0 || index >= size) {
	        throw new IllegalArgumentException("index must be between 0 and size - 1");
	    }

	    if (index == 0) {
	        this.first = this.first.next;
	        if (this.first == null) {
	            this.last = null;
	        }
	        this.size--;
	        return;
	    }

	    Node previous = this.first;
	    for (int i = 0; i < index - 1; i++) {
	        previous = previous.next;
	    }

	    Node target = previous.next;
	    previous.next = target.next;
	    if (target == this.last) {
	        this.last = previous;
	    }
	    this.size--;
	}


	/**
	 * Removes from this list the node pointing to the given memory block.
	 *
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
public void remove(MemoryBlock block) {
    if (block == null) {
        throw new IllegalArgumentException("index must be between 0 and size");
    }

    if (this.first == null) {
        throw new IllegalArgumentException("index must be between 0 and size");
    }

    if (this.first.block.equals(block)) {
        this.first = this.first.next;
        if (this.first == null) {
            this.last = null;
        }
        this.size--;
        return;
    }

    Node current = this.first;
    while (current.next != null) {
        if (current.next.block.equals(block)) {
            current.next = current.next.next;
            if (current.next == null) {
                this.last = current;
            }
            this.size--;
            return;
        }
        current = current.next;
    }

    throw new IllegalArgumentException("index must be between 0 and size");
}




	/** 
	 * returns this lists iterator
	 * */
	public ListIterator iterator() {
		return new ListIterator(this.first);
	}
	

	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
	String s = "";
	Node current = first;
	while (current != null) {
	s = s + current.block + " ";
	current = current.next;
	}
	return s;
	}
}