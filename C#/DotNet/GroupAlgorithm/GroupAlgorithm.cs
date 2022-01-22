using System;
using System.Collections.Generic;
using System.Linq;

namespace GroupAlgorithm
{
	class Record : IComparable<Record>
	{
		public string Name { get; set; }
		public int Quantity { get; set; }

		override
		public string ToString()
		{
			return $"{Name, 5} - {Quantity}";
		}

		public int CompareTo(Record other)
		{
			return String.Compare(this.Name, other.Name);
		}
	}

	class GroupAlgorithm
	{
		static void Main()
		{
			/*List<Record> GetAll()
			{
				return new List<Record>
				{
					new Record { Name = "Radio", Quantity = 3 },
					new Record { Name = "TV", Quantity = 1 },
					new Record { Name = "Radio", Quantity = 2 },
					new Record { Name = "DVD", Quantity = 4 }
				};
			}*/
			void PrintData(string message, List<Record> data)
			{
				Console.WriteLine(message);
				foreach(var item in data)
				{
					Console.WriteLine(item);
				}

			}

			List<Record> records = new List<Record>
			{
				new Record { Name = "Radio", Quantity = 3 },
				new Record { Name = "TV", Quantity = 1 },
				new Record { Name = "Radio", Quantity = 2 },
				new Record { Name = "DVD", Quantity = 4 }
			};

			List<Record> groups = new List<Record>();
			// group 알고리즘 : sort -> sum -> group
			records.Sort();

			foreach (Record record in records)
			{
				if(groups.Count() > 0 && groups.Last().Name == record.Name)
				{
					groups.Last().Quantity += record.Quantity;
				}
				else
				{
					groups.Add(new Record { Name = record.Name, Quantity = record.Quantity });
				}
			}

			PrintData("원본 데이터", records);
			PrintData("그룹 데이터", groups);
			PrintData("Linq", records.GroupBy(r => r.Name)
				.Select(g => new Record { Name = g.Key, Quantity = g.Sum(n => n.Quantity) }).ToList());
		}
	}


}
