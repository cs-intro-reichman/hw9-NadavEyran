public class MemorySpace {
    
    private LinkedList allocatedMemory; // List of allocated memory blocks
    private LinkedList freeMemory; // List of free memory blocks

    /**
     * Constructs a new managed memory space of a given maximal size.
     * @param maxSize the size of the memory space to be managed
     */
    public MemorySpace(int maxSize) {
        allocatedMemory = new LinkedList();
        freeMemory = new LinkedList();
        freeMemory.addLast(new MemoryBlock(0, maxSize));
    }

    /**
     * Allocates a memory block of a requested length (in words).
     * Returns the base address of the allocated block, or -1 if unable to allocate.
     */
    public int malloc(int length) {
        if (freeMemory.getSize() == 0) return -1;

        for (int i = 0; i < freeMemory.getSize(); i++) {
            MemoryBlock freeBlock = freeMemory.getBlock(i);
            
            if (freeBlock.getLength() >= length) {
                int allocatedBaseAddress = freeBlock.getBaseAddress();
                
                if (freeBlock.getLength() == length) {
                    allocatedMemory.addLast(freeBlock);
                    freeMemory.remove(i);
                    return freeBlock.getBaseAddress();
                } else {
                    // Adjust the remaining free memory block
                    int newBaseAddress = freeBlock.getBaseAddress() + length;
                    int newLength = freeBlock.getLength() - length;
                    freeBlock.setBaseAddress(newBaseAddress);
                    freeBlock.setLength(newLength);
                    
                    // Allocate the new memory block
                    MemoryBlock allocatedBlock = new MemoryBlock(allocatedBaseAddress, length);
                    allocatedMemory.addLast(allocatedBlock);
                    return allocatedBlock.getBaseAddress();
                }
            }
        }
        return -1;
    }

    /**
     * Frees the memory block whose base address equals the given address.
     * The block is removed from the allocated list and added to the free list.
     */
    public void free(int address) {
        for (int i = 0; i < allocatedMemory.getSize(); i++) {
            MemoryBlock block = allocatedMemory.getBlock(i);
            if (block.getBaseAddress() == address) {
                allocatedMemory.remove(i);
                freeMemory.addLast(block);
                defrag(); // Merge adjacent free blocks
                return;
            }
        }
    }

    /**
     * Performs defragmentation of free memory blocks.
     * Merges contiguous blocks to create larger free spaces.
     */
    public void defrag() {
        if (freeMemory.getSize() <= 1) return;

        sortFreeListByBaseAddress();
        Node current = freeMemory.getFirst();

        while (current != null && current.next != null) {
            MemoryBlock currentMemoryBlock = current.block;
            MemoryBlock nextMemoryBlock = current.next.block;

            if (currentMemoryBlock.getBaseAddress() + currentMemoryBlock.getLength() == nextMemoryBlock.getBaseAddress()) {
                int newLength = currentMemoryBlock.getLength() + nextMemoryBlock.getLength();
                currentMemoryBlock.setLength(newLength);
                freeMemory.remove(current.next);
            } else {
                current = current.next;
            }
        }
    }

    /**
     * Sorts the free memory list by base address in ascending order.
     */
    private void sortFreeListByBaseAddress() {
        if (freeMemory.getSize() <= 1) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = freeMemory.getFirst();

            while (current != null && current.next != null) {
                MemoryBlock currentMemoryBlock = current.block;
                MemoryBlock nextMemoryBlock = current.next.block;

                if (currentMemoryBlock.getBaseAddress() > nextMemoryBlock.getBaseAddress()) {
                    // Swap the blocks
                    MemoryBlock temp = current.block;
                    current.block = current.next.block;
                    current.next.block = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
