
public class HashQueue<T> {
	T[] arr;
	int maxSize, head, tail;

	public HashQueue(int maxSize) {
        arr = (T[]) new Object[maxSize];
        this.maxSize = maxSize;
        this.head = 0;
        this.tail = 0;
    }

	public boolean isEmpty() {
		if (tail == 0) {
			return true;
		} else
			return false;
	}

	public T peek() {
		if (!isEmpty()) {
			return arr[head];
		} else {
			System.out.println("Queue is empty");
			return null;
		}
	}

	public void enqueue(T text) {
		if (tail < maxSize - 1) {
			arr[tail] = text;
			tail++;
		} else {
			System.out.println("Queue is full");
		}
	}

	public T dequeue() {
		if (!isEmpty()) {
			T temp = arr[head];
			for (int i = 0; i < tail - 1; i++) {
				arr[i] = arr[i + 1];
			}
			arr[tail - 1] = null;
			tail--;
			return temp;
		}
		return null;
	}

	public void display() {
		if (!isEmpty()) {
			for (T t : arr) {
				System.out.print(t + " ");
			}
		} else {
			System.out.println("Nothing to display");
		}
	}
}
