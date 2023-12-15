# PasswordEncode
* 두개의 method를 대표적으로 활용가능 1.encode 2.matches
  * salting
    *     @Override
          public encode(CharSequence rawPassword) {
            if(rawPassword == null) {
                throw new IllegalArgumentException("rawPassword cannot be null");
            }
          String salt = getSalt();
          return BCrypt.hashpw(rawPassword.toString(), salt);
          }
    *     private String getSalt(){
            if(this.random != null){
              return BCrypt.gensalt(this.version.getVersion(), this.strength,this.random);
            }
            }
    * encode method를 사용해야만 올바른 비밀번호 비교가 가능한것.