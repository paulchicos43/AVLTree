class Node:

	def __init__(self, val, next = None):
		self.val = val
		self.next = next

class List:

	def __init__(self):
		self.head = None

	def add(self, value):
		if self.head == None:
			self.head = Node(value)
		else:
			self.holder = self.head
			while self.holder.next != None:
				self.holder = self.holder.next
			self.holder.next = Node(value)



myList = List()
myList.add("Hello")
myList.add("Thing")
print(myList.head.val)
print(myList.head.next.val)
