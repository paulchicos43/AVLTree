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
	def print(self):
		holder = self.head
		while holder.next != None:
			print(holder.val, end = " ")
			holder = holder.next

list = List()
while True:
	list.add(input("Enter something to add to the list: "))
	if input("Done? ") == "Done":
		print(list.print())
		exit()
