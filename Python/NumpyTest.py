import numpy as np
v1=np.array([1,2,3,4])
print(v1)
# 연속되거나 일정한 규칙을 가진 숫자
v2=np.arange(5)
print(v2)
# 연속되거나 일정한 규칙을 가진 숫자, 데이터 형태 지정
v3=np.arange(1,10,2,dtype=int)
print(v3)
v4=np.arange(3.5, 10.5, 2, dtype=float)
print(v4)
#제곱값 생성
v5=np.arange(1,9,1)**2
print(v5)
# 세 제곱값 생성
v6=np.arange(1,5,1)**3
print(v6)
# order='T' 행부터 할당, order='F' = 열부터 할당, default는 order='T'
v7=v1.reshape(2,6,order='F')

# v1=np.arange(1,10,1)
# print(v1)

# v2=v1**2
# print(v2)

# v3 = v1 **3
# print(v3)v1=np.arange(1,10,1)
# print(v1)

# v2=v1**2
# print(v2)

# v3 = v1 **3
# print(v3)
