
    function validate()
    {
        if (formSearch.search.value === "")
    {
        alert("搜索框不能为空，请重新输入");
        formSearch.search.focus();
        return false;
    }

}

